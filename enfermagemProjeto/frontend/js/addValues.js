const dados = {
    nome: "Paracetamol",
    tipoRemedio: "Analgésico",
    restricao: "Uso controlado",
    contraIndicacoes: "Pessoas com alergia à substância",
    efeitos: "Sonolência e náusea",
    substancia: "Paracetamol",
    substancia_tipo: "Sintética",
    tarja: "Vermelha",
    validade: "24 meses",
    conservacao: "Local seco e refrigerado",
    empresa: "Farmacêutica Exemplo LTDA",
    cidade: "Londrina",
    uf: "PR"
};

document.addEventListener("keyup", e=>{
    if (e.key !== "Enter") return
// Preenche inputs e selects
Object.entries(dados).forEach(([id, valor]) => {
    const campo = document.getElementById(id);

    if (campo && campo.type !== "file") {
        campo.value = valor;
    }
});

// Checkboxes
["criancas", "adultos"].forEach(id => {
    document.getElementById(id).checked = true;
});
})
