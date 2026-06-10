(() => {
const campoBusca = document.getElementById("empresaBusca");
const lista = document.getElementById("listaEmpresas");
const campoEmpresa = document.getElementById("empresa");
const botaoTrocar = document.getElementById("trocarEmpresa");

campoBusca.addEventListener("input", async () => {

    const texto = campoBusca.value.trim();
    botaoTrocar.style.display = "none";

    if (texto.length < 2) {

        lista.innerHTML = "";

        return;
    }
    const response = await fetch(
        `http://localhost:8080/remedios/api/empresas/busca?nome=${encodeURIComponent(texto)}`
    );

    const empresas = await response.json();

    lista.innerHTML = "";
    
    empresas.forEach(empresa => {

        const item =
            document.createElement("button");

        item.type = "button";

        item.className =
            "list-group-item list-group-item-action";

        item.textContent =
            empresa.nome;

        item.addEventListener("click", () => {

    campoBusca.value = empresa.nome;
    campoEmpresa.value = empresa.id;

    campoBusca.readOnly = true;

    lista.innerHTML = "";

    document.getElementById("trocarEmpresa")
        .style.display = "inline-block";
});

        lista.appendChild(item);
    });
});

botaoTrocar.addEventListener("click", () => {

    campoBusca.readOnly = false;

    campoBusca.value = "";
    campoEmpresa.value = "";

    lista.innerHTML = "";

    botaoTrocar.style.display = "none";

    campoBusca.focus();
});
})()