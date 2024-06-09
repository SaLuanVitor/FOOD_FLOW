package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.models.Menu;
import com.example.springboot.models.Mesa;
import com.example.springboot.models.Pedido;
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
        pedido.setFuncionario(funcionarioController.getFuncionarioLogado());
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/atualizar/{id}")
    public String updatePedido(@PathVariable(value = "id") Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).get();
        List<Mesa> mesas = mesaRepository.findAll();
        List<Menu> itensMenu = menuRepository.findAll();
        model.addAttribute("pedido", pedido);
        model.addAttribute("mesas", mesas);
        model.addAttribute("itensMenu", itensMenu);
        return "pedidos/atualizar";
    }

    @GetMapping("/pedidos/excluir/{id}")
    public String deletePedidoById(@PathVariable(value = "id") Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }

}
