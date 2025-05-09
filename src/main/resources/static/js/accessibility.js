document.addEventListener('DOMContentLoaded', function() {
    // Controle de Tamanho da Fonte
    const increaseFontBtn = document.getElementById('increaseFontSize');
    const decreaseFontBtn = document.getElementById('decreaseFontSize');
    const resetFontBtn = document.getElementById('resetFontSize');

    let currentFontSize = parseInt(localStorage.getItem('fontSize')) || 100;
    updateFontSize(); // Aplica o tamanho salvo ao carregar a página

    increaseFontBtn.addEventListener('click', function() {
        if (currentFontSize < 150) {
            currentFontSize += 10;
            updateFontSize();
        }
    });

    decreaseFontBtn.addEventListener('click', function() {
        if (currentFontSize > 70) {
            currentFontSize -= 10;
            updateFontSize();
        }
    });

    resetFontBtn.addEventListener('click', function() {
        currentFontSize = 100;
        updateFontSize();
    });

    function updateFontSize() {
        document.documentElement.style.fontSize = `${currentFontSize}%`;
        localStorage.setItem('fontSize', currentFontSize);
    }
});