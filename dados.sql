INSERT INTO SUBSTANCIAS (NOME, TIPO) VALUES
('Paracetamol', 'Analgésico e antitérmico'),
('Ibuprofeno', 'Anti-inflamatório não esteroidal'),
('Amoxicilina', 'Antibiótico betalactâmico'),
('Omeprazol', 'Inibidor da bomba de prótons'),
('Losartana Potássica', 'Antagonista do receptor de angiotensina II'),
('Atorvastatina', 'Inibidor da HMG-CoA redutase'),
('Metformina', 'Biguanida antidiabética'),
('Azitromicina', 'Antibiótico macrolídeo'),
('Dipirona Sódica', 'Analgésico e antitérmico'),
('Sinvastatina', 'Inibidor da HMG-CoA redutase'),
('Clonazepam', 'Benzodiazepínico anticonvulsivante'),
('Fluoxetina', 'Inibidor seletivo da recaptação de serotonina'),
('Dexametasona', 'Corticosteroide'),
('Cetirizina', 'Anti-histamínico'),
('Metoprolol', 'Betabloqueador seletivo'),
('Insulina Glargina', 'Insulina de ação prolongada'),
('Esomeprazol', 'Inibidor da bomba de prótons'),
('Ivermectina', 'Antiparasitário'),
('Ácido Acetilsalicílico', 'Antiagregante plaquetário e analgésico'),
('Prednisona', 'Corticosteroide oral');

INSERT INTO PRESCRICOES (RESTRICAO, CONTRA_INDICACOES, EFEITOS, VALIDADE, CONSERVACAO) VALUES
('Uso adulto e pediátrico acima de 2 anos', 'Hipersensibilidade ao paracetamol, insuficiência hepática grave', 'Náusea, reações alérgicas, hepatotoxicidade em doses elevadas', '24 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto e pediátrico acima de 6 meses', 'Úlcera péptica ativa, insuficiência renal grave, último trimestre de gravidez', 'Dor abdominal, náusea, sangramento gastrointestinal', '36 meses', 'Temperatura ambiente, protegido da umidade'),
('Uso adulto e pediátrico', 'Hipersensibilidade a penicilinas ou cefalosporinas', 'Diarreia, náusea, erupções cutâneas, candidíase oral', '24 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto', 'Hipersensibilidade ao omeprazol, uso concomitante com nelfinavir', 'Cefaleia, diarreia, náusea, dor abdominal', '24 meses', 'Temperatura ambiente, abaixo de 25°C, protegido da umidade'),
('Uso adulto', 'Hipersensibilidade à losartana, gravidez, insuficiência hepática grave', 'Tontura, hipotensão, hipercalemia, elevação de creatinina', '36 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto', 'Miopatia ativa, doença hepática ativa, gravidez, amamentação', 'Mialgia, elevação de CPK, cefaleia, distúrbios gastrointestinais', '36 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto', 'Insuficiência renal grave, insuficiência hepática, cetoacidose diabética', 'Náusea, diarreia, dor abdominal, acidose lática em casos raros', '36 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto e pediátrico acima de 6 meses', 'Hipersensibilidade a macrolídeos, uso com ergotamina', 'Diarreia, náusea, dor abdominal, elevação de transaminases', '24 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto e pediátrico acima de 3 meses', 'Hipersensibilidade à dipirona, disfunção da medula óssea', 'Agranulocitose, reações alérgicas, hipotensão', '36 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto', 'Miopatia ativa, doença hepática ativa, gravidez, amamentação', 'Mialgia, cefaleia, distúrbios gastrointestinais, elevação de CPK', '36 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto', 'Hipersensibilidade ao clonazepam, glaucoma agudo, insuficiência hepática grave', 'Sonolência, ataxia, depressão, dependência física', '60 meses', 'Temperatura ambiente, abaixo de 25°C, protegido da luz'),
('Uso adulto', 'Hipersensibilidade à fluoxetina, uso com IMAOs', 'Insônia, ansiedade, náusea, disfunção sexual, cefaleia', '24 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto e pediátrico', 'Infecções fúngicas sistêmicas, hipersensibilidade a corticosteroides', 'Retenção hídrica, hiperglicemia, imunossupressão, osteoporose', '24 meses', 'Temperatura ambiente, abaixo de 25°C, protegido da luz'),
('Uso adulto e pediátrico acima de 2 anos', 'Hipersensibilidade à cetirizina ou hidroxizina', 'Sonolência leve, cefaleia, boca seca, náusea', '36 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto', 'Bradicardia sinusal, bloqueio AV de 2° e 3° grau, insuficiência cardíaca descompensada', 'Bradicardia, fadiga, extremidades frias, broncoespasmo', '36 meses', 'Temperatura ambiente, abaixo de 25°C'),
('Uso adulto e pediátrico', 'Hipoglicemia, hipersensibilidade à insulina glargina', 'Hipoglicemia, reações no local de aplicação, lipodistrofia', '36 meses', 'Refrigerar entre 2°C e 8°C, não congelar'),
('Uso adulto', 'Hipersensibilidade ao esomeprazol, uso com nelfinavir', 'Cefaleia, diarreia, náusea, flatulência', '24 meses', 'Temperatura ambiente, abaixo de 30°C'),
('Uso adulto e pediátrico acima de 15 kg', 'Hipersensibilidade à ivermectina, gravidez', 'Tontura, náusea, diarreia, prurido, reação de Mazzotti', '24 meses', 'Temperatura ambiente, abaixo de 30°C, protegido da luz'),
('Uso adulto', 'Úlcera péptica ativa, hemofilia, hipersensibilidade ao AAS, último trimestre de gravidez', 'Sangramento gastrointestinal, zumbido, broncoespasmo em asmáticos', '60 meses', 'Temperatura ambiente, abaixo de 25°C, protegido da umidade'),
('Uso adulto e pediátrico', 'Infecções fúngicas sistêmicas, hipersensibilidade a corticosteroides, úlcera péptica ativa', 'Hiperglicemia, retenção hídrica, supressão adrenal, osteoporose', '36 meses', 'Temperatura ambiente, abaixo de 25°C');

-- REMEDIOS
-- ID_EMPRESA: 3=Aché, 13=Bayer, 32=EMS, 33=Eli Lilly, 35=Eurofarma, 43=Germed,
--             53=Janssen, 67=Novartis, 71=Novo Nordisk, 78=Pfizer, 80=Raia Drogasil,
--             85=Sanofi Aventis, 86=Sanofi Medley, 95=União Química
INSERT INTO REMEDIOS (NOME, BULA, TIPO, ID_USUARIO, ID_PRESCRICAO, ID_TARJA, ID_SUBSTANCIA, ID_EMPRESA) VALUES
('Tylenol', 'bulas/bula.pdf', 'Comprimido', NULL, 1, 1, 1, 3),
('Advil', 'bulas/bula.pdf', 'Comprimido', NULL, 2, 1, 2, 13),
('Amoxil', 'bulas/bula.pdf', 'Cápsula', NULL, 3, 2, 3, 78),
('Losec', 'bulas/bula.pdf', 'Cápsula', NULL, 4, 1, 4, 85),
('Cozaar', 'bulas/bula.pdf', 'Comprimido', NULL, 5, 2, 5, 67),
('Lipitor', 'bulas/bula.pdf', 'Comprimido', NULL, 6, 2, 6, 78),
('Glifage XR', 'bulas/bula.pdf', 'Comprimido', NULL, 7, 2, 7, 3),
('Azitrom', 'bulas/bula.pdf', 'Comprimido', NULL, 8, 2, 8, 35),
('Novalgina', 'bulas/bula.pdf', 'Comprimido', NULL, 9, 1, 9, 85),
('Zocor', 'bulas/bula.pdf', 'Comprimido', NULL, 10, 2, 10, 67),
('Rivotril', 'bulas/bula.pdf', 'Comprimido', NULL, 11, 3, 11, 67),
('Prozac', 'bulas/bula.pdf', 'Cápsula', NULL, 12, 2, 12, 33),
('Decadron', 'bulas/bula.pdf', 'Comprimido', NULL, 13, 2, 13, 86),
('Zyrtec', 'bulas/bula.pdf', 'Comprimido', NULL, 14, 1, 14, 67),
('Selozok', 'bulas/bula.pdf', 'Comprimido', NULL, 15, 2, 15, 67),
('Basaglar', 'bulas/bula.pdf', 'Injetável', NULL, 16, 2, 16, 33),
('Nexium', 'bulas/bula.pdf', 'Comprimido', NULL, 17, 1, 17, 85),
('Stromectol', 'bulas/bula.pdf', 'Comprimido', NULL, 18, 1, 18, 86),
('AAS', 'bulas/bula.pdf', 'Comprimido', NULL, 19, 1, 19, 13),
('Meticorten', 'bulas/bula.pdf', 'Comprimido', NULL, 20, 2, 20, 67);

INSERT INTO REMEDIOS_PUBLICO_ALVO (ID_REMEDIO, ID_PUBLICO_ALVO) VALUES
(1, 1), (1, 2), (1, 3),        -- Tylenol: crianças, adultos, idosos
(2, 2), (2, 3),                -- Advil: adultos, idosos
(3, 1), (3, 2), (3, 3),        -- Amoxil: crianças, adultos, idosos
(4, 2), (4, 3),                -- Losec: adultos, idosos
(5, 2), (5, 3),                -- Cozaar: adultos, idosos
(6, 2), (6, 3),                -- Lipitor: adultos, idosos
(7, 2), (7, 3),                -- Glifage: adultos, idosos
(8, 1), (8, 2), (8, 3),        -- Azitrom: crianças, adultos, idosos
(9, 1), (9, 2), (9, 3),        -- Novalgina: crianças, adultos, idosos
(10, 2), (10, 3),              -- Zocor: adultos, idosos
(11, 2),                       -- Rivotril: adultos
(12, 2),                       -- Prozac: adultos
(13, 1), (13, 2), (13, 3),     -- Decadron: crianças, adultos, idosos
(14, 1), (14, 2), (14, 3),     -- Zyrtec: crianças, adultos, idosos
(15, 2), (15, 3),              -- Selozok: adultos, idosos
(16, 1), (16, 2), (16, 3),     -- Basaglar: crianças, adultos, idosos
(17, 2), (17, 3),              -- Nexium: adultos, idosos
(18, 2), (18, 3),              -- Stromectol: adultos, idosos
(19, 2), (19, 3),              -- AAS: adultos, idosos
(20, 2), (20, 3);              -- Meticorten: adultos, idosos