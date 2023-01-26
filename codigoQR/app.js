const contenedorQR = document.getElementById('contenedorQR');
const formulario = document.getElementById('formulario');

const QR = new QRCode(contenedorQR);

formulario.addEventListener('submit', (e) => {
    // Previene que la pagina se recargue
    e.preventDefault();

    QR.makeCode(formulario.codigoS10.value);
});
