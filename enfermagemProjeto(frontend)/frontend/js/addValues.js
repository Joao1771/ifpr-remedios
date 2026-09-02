(() => {const dados = {
    nome: "Paracetamol",
    tipoRemedio: "Analgésico",
    restricao: "Uso controlado",
    contraIndicacoes: "Pessoas com alergia à substância",
    efeitos: "Sonolência e náusea",
    substancia: "Paracetamol base",
    substancia_tipo: "Sintética",
    tarja: "2",
    validade: "24 meses",
    conservacao: "Local seco e refrigerado",
    empresa: "1"
};
document.addEventListener("keyup", e => {

    if (e.key !== "Ç") return;
    
    // Preenche inputs e selects
    Object.entries(dados).forEach(([id, valor]) => {

        const campo = document.getElementById(id);

        if (!campo) return;

        if (campo.type !== "file") {
        
            campo.value = valor;

            campo.dispatchEvent(new Event("change"));
        }
    });

    // Preenche público-alvo com crianças e adultos
    ["criancas", "adultos"].forEach(id => {

        const checkbox = document.getElementById(id);

        if (checkbox) {
            checkbox.checked = true;
        }
    });

});
})()