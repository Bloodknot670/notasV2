const obtenerComentarios = async () => {
    try {
        const response = await fetch('/coments/todosComentarios');
        if (response.ok) {
            return await response.json();
        } else {
            throw new Error('Error al obtener los comentarios');
        }
    } catch (error) {
        console.error(error);
        return []; // Devuelve un arreglo vacío en caso de error
    }
}

const guardarRespuesta = async(comentarioId, respuesta, usuarioId)=>{

    const fechaHoraActual = new Date();
    const anio = fechaHoraActual.getFullYear();
    const mes = String(fechaHoraActual.getMonth() + 1).padStart(2, '0');
    const dia = String(fechaHoraActual.getDate()).padStart(2, '0');
    const hora = String(fechaHoraActual.getHours()).padStart(2, '0');
    const minutos = String(fechaHoraActual.getMinutes()).padStart(2, '0');
    const segundos = String(fechaHoraActual.getSeconds()).padStart(2, '0');

    const fechaHoraFormateada = `${anio}-${mes}-${dia}T${hora}:${minutos}:${segundos}.000Z`;

    let fechaRespuesta = fechaHoraFormateada;
    let textoRespuesta =respuesta;
    let idUsuario = usuarioId;

    const jsonSend = {
        fechaRespuesta,
        textoRespuesta,
        usuario: {
            idUsuario: parseInt(idUsuario)
        },
        comentario:{
            idComentario:parseInt(comentarioId)
        }
    }

    console.log("Json a enviar",JSON.stringify(jsonSend))
    const url = "http://localhost:3000/respuesta/nuevaRespuesta";
    const response = await fetch(
        url,
        {
            method:'Post',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(jsonSend)
        }
    )

    const data = await response.json()

    await mostrarComentarios();
}
const guardarComentario = async (divNota) => {
    console.log("de que nota viene ",divNota)
    const fechaHoraActual = new Date();
    const anio = fechaHoraActual.getFullYear();
    const mes = String(fechaHoraActual.getMonth() + 1).padStart(2, '0');
    const dia = String(fechaHoraActual.getDate()).padStart(2, '0');
    const hora = String(fechaHoraActual.getHours()).padStart(2, '0');
    const minutos = String(fechaHoraActual.getMinutes()).padStart(2, '0');
    const segundos = String(fechaHoraActual.getSeconds()).padStart(2, '0');

    const fechaHoraFormateada = `${anio}-${mes}-${dia}T${hora}:${minutos}:${segundos}.000Z`;


    console.log("hora", fechaHoraFormateada)

    let fechaComentario = fechaHoraFormateada;
    let textoComentario = document.getElementById(`text-coment-${divNota}`).value;
    let idUsuario = document.getElementById('usr-id').value;
    let idNota = document.getElementById(`id-nota-${divNota}`).value;
    const jsonSend = {
        fechaComentario: fechaComentario,
        textoComentario: textoComentario,
        usuario: {
            idUsuario: parseInt(idUsuario)
        },
        nota:{
            idNota:parseInt(idNota)
        }
    }


    const url = "http://localhost:3000/coments/nuevoComentario";

    const response = await fetch(url, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(jsonSend),

    })
    await mostrarComentarios()
}


const mostrarComentarios = async () => {
    try {
        const comentarios = await obtenerComentarios();

        if (comentarios.length === 0) {
            document.getElementById("coments").innerHTML = "No hay comentarios";
        } else {
            // Objeto para almacenar los comentarios por nota
            const comentariosPorNota = {};

            // Agrupar los comentarios por nota
            comentarios.forEach(comentario => {
                const idNota = comentario.idNota;

                // Verificar si la nota ya tiene un array de comentarios
                if (!comentariosPorNota[idNota]) {
                    comentariosPorNota[idNota] = [];
                }

                comentariosPorNota[idNota].push(comentario);
            });

            // Mostrar los comentarios en los divs correspondientes
            for (const idNota in comentariosPorNota) {
                const div = document.getElementById(`div-${idNota}`);
                const comentariosNota = comentariosPorNota[idNota];

                // Limpiar el contenido previo
                div.innerHTML = '';

                // Mostrar los comentarios en el div
                comentariosNota.forEach(comentario => {
                    const card = document.createElement('div');
                    card.classList.add('card', 'text-withe','mb-3');
                    card.style.backgroundColor = '#94B7B4'
                    const cardBody = document.createElement('div');
                    cardBody.classList.add('card-body');

                    const textoComentario = document.createElement('p');
                    textoComentario.classList.add('card-text');
                    textoComentario.textContent = comentario.textoComentario;

                    const nombreUsuario = document.createElement('p');
                    nombreUsuario.classList.add('card-text');
                    nombreUsuario.textContent = `${comentario.nombreUsuario} - ${comentario.idUsr === 1 ? 'Interno' : 'Externo'}`;

                    const fechaComentario = document.createElement('p');
                    fechaComentario.classList.add('card-text');
                    fechaComentario.textContent = new Date(comentario.fechaComentario).toLocaleString();

                    cardBody.appendChild(textoComentario);
                    cardBody.appendChild(nombreUsuario);
                    cardBody.appendChild(fechaComentario);

                    // Agregar formulario para respuestas
                    const respuestaForm = document.createElement('form');
                    respuestaForm.classList.add('respuesta-form');

                    const respuestaInput = document.createElement('input');
                    respuestaInput.type = 'text';
                    respuestaInput.classList.add('form-control');
                    respuestaInput.placeholder = 'Escribe una respuesta...';
                    respuestaInput.name = 'respuesta';


                    const enviarRespuestaBtn = document.createElement('button');
                    enviarRespuestaBtn.type = 'submit';
                    enviarRespuestaBtn.classList.add('btn', 'btn-primary');
                    enviarRespuestaBtn.textContent = 'Enviar';
                    enviarRespuestaBtn.style.backgroundColor = "#215B57"
                    enviarRespuestaBtn.style.color = "#F8F8F8"
                    enviarRespuestaBtn.onclick = ()=>{location.href="http://localhost:3000/notas/todasNotas"}
                    respuestaForm.appendChild(respuestaInput);
                    respuestaForm.appendChild(enviarRespuestaBtn);

                    respuestaForm.addEventListener('submit', async (event) => {
                        event.preventDefault();
                        const respuesta = respuestaInput.value;
                        const comentarioId = comentario.idComentario;
                        const usuarioId = parseInt(document.getElementById('usr-id').value);
                        await guardarRespuesta(comentarioId, respuesta, usuarioId);

                        window.location.reload()
                    });

                    cardBody.appendChild(respuestaForm);

                    card.appendChild(cardBody);

                    // Verificar si el comentario tiene respuestas
                    if (comentario.respuestas.length > 0) {
                        const divRespuestas = document.createElement('div');
                        divRespuestas.classList.add('respuestas');

                        // Mostrar las respuestas
                        comentario.respuestas.forEach(respuesta => {
                            const cardRespuesta = document.createElement('div');
                            cardRespuesta.classList.add('card', 'text-withe','m-3');
                            cardRespuesta.style.backgroundColor = '#689592'

                            const cardBodyRespuesta = document.createElement('div');
                            cardBodyRespuesta.classList.add('card-body');

                            const textoRespuesta = document.createElement('p');
                            textoRespuesta.classList.add('card-text');
                            textoRespuesta.textContent = respuesta.textoRespuesta;

                            const nombreUsuarioRespuesta = document.createElement('p');
                            nombreUsuarioRespuesta.classList.add('card-text');
                            nombreUsuarioRespuesta.textContent = `${respuesta.nombreUsuario} - ${respuesta.usuarioId === 1 ? 'Interno' : 'Externo'}`;

                            const fechaRespuesta = document.createElement('p');
                            fechaRespuesta.classList.add('card-text');
                            fechaRespuesta.textContent = new Date(respuesta.fechaRespuesta).toLocaleString();

                            cardBodyRespuesta.appendChild(textoRespuesta);
                            cardBodyRespuesta.appendChild(nombreUsuarioRespuesta);
                            cardBodyRespuesta.appendChild(fechaRespuesta);

                            cardRespuesta.appendChild(cardBodyRespuesta);
                            divRespuestas.appendChild(cardRespuesta);
                        });


                        card.appendChild(divRespuestas);
                    }

                    div.appendChild(card);
                });
            }
        }
    } catch (error) {
        console.error(error);
    }
}

function obtenerValoresRespuesta(input){
    const value = input.value;
    return value
}
