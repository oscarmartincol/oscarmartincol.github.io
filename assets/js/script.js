document.addEventListener('DOMContentLoaded', function() {
    // Año actual para el copyright
    document.getElementById('year').textContent = new Date().getFullYear();
    
    // Verificar si hay un idioma guardado en localStorage
    const savedLanguage = localStorage.getItem('portfolioLanguage') || 'es';
    changeLanguage(savedLanguage);
    
    // Marcar el botón activo
    document.querySelectorAll('.lang-btn').forEach(btn => {
        btn.classList.remove('active');
        if (btn.textContent === savedLanguage.toUpperCase()) {
            btn.classList.add('active');
        }
    });
});

function copyEmail() {
    const email = document.getElementById('email').textContent;
    const currentLang = localStorage.getItem('portfolioLanguage') || 'es';
    const message = currentLang === 'es' 
        ? 'Correo copiado al portapapeles: ' 
        : 'Email copied to clipboard: ';
    
    navigator.clipboard.writeText(email).then(() => {
        alert(message + email);
    }).catch(err => {
        console.error('Error al copiar el correo: ', err);
    });
}

function changeLanguage(lang) {
    // Guardar preferencia de idioma
    localStorage.setItem('portfolioLanguage', lang);
    
    // Actualizar botones de idioma
    document.querySelectorAll('.lang-btn').forEach(btn => {
        btn.classList.remove('active');
        if (btn.textContent === lang.toUpperCase()) {
            btn.classList.add('active');
        }
    });
    
    // Cambiar el título de la página
    const title = document.querySelector('title');
    title.textContent = title.getAttribute(`data-${lang}`);
    
    // Cambiar todos los elementos con atributos data-es y data-en
    document.querySelectorAll('[data-es], [data-en]').forEach(element => {
        if (element.hasAttribute(`data-${lang}`)) {
            const text = element.getAttribute(`data-${lang}`);
            
            // Para elementos de entrada (input, textarea, etc.)
            if (element.hasAttribute('value')) {
                element.value = text;
            } 
            // Para otros elementos
            else {
                element.textContent = text;
            }
        }
    });
}