document.addEventListener("DOMContentLoaded", function() {
    const senhaInput = document.getElementById('senha');
    const olho = document.getElementById('togglePassword');

    olho.addEventListener('click', function() {
        let inputTypePassword = senhaInput.type == "password";

        if (inputTypePassword) {
            showPassword();
            olho.setAttribute("name", "eye-outline");
        } else {
            hidePassword();
            olho.setAttribute("name", "eye-off-outline");
        }
    });

    function showPassword(){
        senhaInput.setAttribute("type", "text");
    }

    function hidePassword(){
        senhaInput.setAttribute("type", "password");
    }
});

