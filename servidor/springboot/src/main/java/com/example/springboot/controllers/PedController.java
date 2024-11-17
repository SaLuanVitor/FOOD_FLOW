package com.example.springboot.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.models.HistoricoPedido;
import com.example.springboot.models.Menu;
import com.example.springboot.models.Mesa;
import com.example.springboot.models.Pedido;
import com.example.springboot.repositories.HistoricoPedidoRepository;
import com.example.springboot.repositories.MenuRepository;
import com.example.springboot.repositories.MesaRepository;
import com.example.springboot.repositories.PedRepository;

@Controller
public class PedController {

    @Autowired
    PedRepository pedidoRepository;

    @Autowired
    MesaRepository mesaRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    HistoricoPedidoRepository historicoPedidoRepository;

    @Autowired
    FuncController funcionarioController;

    @GetMapping("/pedidos")
    public String getAllPedidosPage(final Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/pedidos";
    }

    @GetMapping("/pedidos/adicionar")
    public String addNewPedido(Model model) {
        Pedido pedido = new Pedido();
        List<Mesa> mesas = mesaRepository.findAll();
        List<Menu> itensMenu = menuRepository.findAll();
        model.addAttribute("pedido", pedido);
        model.addAttribute("mesas", mesas);
        model.addAttribute("itensMenu", itensMenu);
        return "pedidos/adicionar";
    }

    @PostMapping("/pedidos/salvar")
    public String savePedido(@ModelAttribute("pedido") Pedido pedido) {
        // Define o funcionário logado
        pedido.setFuncionario(funcionarioController.getFuncionarioLogado());

        // Garante que o dataStatus inicial é definido
        pedido.setDataStatus(LocalDateTime.now());

        // Salva o pedido
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Cria o registro no histórico
        HistoricoPedido historico = new HistoricoPedido();
        historico.setPedido(pedidoSalvo);
        historico.setStatusAnterior(null); // Pedido novo, não há status anterior
        historico.setStatusAtual(pedido.getStatus());
        historico.setUsuario(funcionarioController.getFuncionarioLogado());
        historico.setDataHistorico(LocalDateTime.now());
        historicoPedidoRepository.save(historico);

        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/atualizar/{id}")
    public String updatePedido(@PathVariable(value = "id") Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
            return "redirect:/pedidos"; // Redireciona se o pedido não for encontrado
        }

        List<Mesa> mesas = mesaRepository.findAll();
        List<Menu> itensMenu = menuRepository.findAll();

        model.addAttribute("pedido", pedido);
        model.addAttribute("mesas", mesas);
        model.addAttribute("itensMenu", itensMenu);
        return "pedidos/atualizar";
    }

    @PostMapping("/pedidos/atualizar")
    public String updatePedidoPost(@ModelAttribute("pedido") Pedido pedido) {
        // Busca o pedido existente no banco
        Pedido pedidoExistente = pedidoRepository.findById(pedido.getIdPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        // Cria o registro no histórico
        HistoricoPedido historico = new HistoricoPedido();
        historico.setPedido(pedidoExistente);
        historico.setStatusAnterior(pedidoExistente.getStatus());
        historico.setStatusAtual(pedido.getStatus());
        historico.setUsuario(funcionarioController.getFuncionarioLogado());
        historico.setDataHistorico(LocalDateTime.now());
        historicoPedidoRepository.save(historico);

        // Atualiza o status e outros dados
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setTempoPreparo(pedido.getTempoPreparo());
        pedidoExistente.setItensMenu(pedido.getItensMenu());
        pedidoExistente.setDataStatus(LocalDateTime.now());

        // Salva o pedido atualizado
        pedidoRepository.save(pedidoExistente);

        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/excluir/{id}")
    public String deletePedidoById(@PathVariable(value = "id") Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }
}
