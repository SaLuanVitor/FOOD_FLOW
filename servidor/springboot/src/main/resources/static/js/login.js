var timer; // Variável global para armazenar o temporizador
var progressBar; // Variável global para a barra de progresso
var progressBarWidth; // Variável global para a largura atual da barra de progresso
var animationDuration = 3000; // Duração da animação em milissegundos
var paused = false; // Variável para controlar se a animação está pausada

function login() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // Verifica se os campos de usuário e senha estão vazios ou nulos
    if (!username || !password) {
        showPopup("Por favor, preencha todos os campos.");
        return; // Retorna imediatamente se algum dos campos estiver vazio ou nulo
    }

    var loginData = {
        nome: username,
        senha: password
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/login", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Login bem-sucedido, exibe popup com mensagem e fecha em 3 segundos
                showPopup("Login bem-sucedido!");
                startTimer(); // Inicia o temporizador
            } else if (xhr.status === 401) {
                // Senha incorreta
                showPopup("Senha incorreta! Por favor, tente novamente.");
                startTimer(); // Inicia o temporizador
            } else if (xhr.status === 404) {
                // Usuário não encontrado
                showPopup("Usuário não encontrado! Por favor, verifique o nome de usuário e tente novamente.");
                startTimer(); // Inicia o temporizador
            }
        }
    };
    xhr.send(JSON.stringify(loginData));
}

function showPopup(message) {
    var popupContainer = document.getElementById('popup-container');
    var popupMessage = document.getElementById('popup-message');
    popupMessage.textContent = message;
    popupContainer.classList.add('active');
    
    // Adiciona a barra de progresso dentro da mensagem
    progressBar = document.createElement('div');
    progressBar.className = 'progress-bar';
    progressBar.style.animationDuration = animationDuration + 'ms'; // Define a duração da animação
    popupMessage.appendChild(progressBar);
    
    // Obtém a largura inicial da barra de progresso
    progressBarWidth = progressBar.offsetWidth;
    
    // Inicia o temporizador para fechar o popup após o tempo limite
    startTimer();
}

function hidePopup() {
    var popupContainer = document.getElementById('popup-container');
    popupContainer.classList.remove('active');
    
    // Remove a barra de progresso
    if (progressBar && progressBar.parentNode) {
        progressBar.parentNode.removeChild(progressBar);
    }
}

function startTimer() {
    clearTimeout(timer); // Limpa o temporizador anterior (se existir)
    
    timer = setInterval(function () {
        if (!paused) {
            if (progressBarWidth <= 0) {
                clearInterval(timer); // Limpa o temporizador quando a barra de progresso atinge 0%
                hidePopup(); // Esconde a mensagem quando o tempo acabar
            } else {
                progressBarWidth -= 1; // Reduz a largura da barra de progresso em 1
                progressBar.style.width = progressBarWidth + 'px'; // Atualiza a largura da barra de progresso
            }
        }
    }, animationDuration / progressBarWidth); // Intervalo de atualização da barra de progresso
}

// Adiciona eventos de mouseover e mouseout à div que contém a mensagem para pausar e retomar a animação
document.getElementById('popup-container').addEventListener('mouseover', function () {
    paused = true; // Pausa a animação quando o mouse está sobre a div
    progressBar.style.animationPlayState = 'paused'; // Pausa a animação
});

document.getElementById('popup-container').addEventListener('mouseout', function () {
    paused = false; // Retoma a animação quando o mouse sai da div
    progressBar.style.animationPlayState = 'running'; // Retoma a animação
});
