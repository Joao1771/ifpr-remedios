DROP SCHEMA IF EXISTS remedios_db;
CREATE SCHEMA remedios_db
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;	
USE remedios_db;

-- CREATE USER 'appuser'@'localhost' IDENTIFIED BY '1234';
-- GRANT SELECT, INSERT, UPDATE, DELETE
-- ON remedios_db.*
-- TO 'appuser'@'%';

CREATE TABLE USUARIOS
(
    ID_USUARIO INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    EMAIL VARCHAR(150) NOT NULL UNIQUE,
    SENHA VARCHAR(255) NOT NULL,
    TIPO VARCHAR(20) NOT NULL
);

CREATE TABLE PRESCRICOES
(
    ID_PRESCRICAO INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    RESTRICAO VARCHAR(250) NOT NULL,
    CONTRA_INDICACOES VARCHAR(2000) NOT NULL,
    EFEITOS VARCHAR(1000) NOT NULL,
    VALIDADE VARCHAR(250) NOT NULL,
    CONSERVACAO VARCHAR(100) NOT NULL
);

CREATE TABLE SUBSTANCIAS
(
    ID_SUBSTANCIA INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NOME VARCHAR(200) NOT NULL,
    TIPO VARCHAR(100) NOT NULL
);

CREATE TABLE PUBLICO_ALVO
(
    ID_PUBLICO_ALVO INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NOME VARCHAR(50) NOT NULL
);

CREATE TABLE TARJAS
(
    ID_TARJA INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NOME VARCHAR(30) NOT NULL
);

CREATE TABLE CIDADES
(
    ID_CIDADE INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NOME VARCHAR(200) NOT NULL,
    UF CHAR(2)
);

CREATE TABLE EMPRESAS
(
    ID_EMPRESA INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NOME VARCHAR(100) NOT NULL,
    CNPJ CHAR(14) UNIQUE NOT NULL,
    ID_CIDADE INT NOT NULL,

    FOREIGN KEY (ID_CIDADE)
    REFERENCES CIDADES(ID_CIDADE)
);

	CREATE TABLE REMEDIOS
	(
		ID_REMEDIO INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
		NOME VARCHAR(100) NOT NULL,
		BULA VARCHAR(255) NOT NULL,
		TIPO VARCHAR(20) NOT NULL,

		ID_USUARIO INT,
		ID_PRESCRICAO INT NOT NULL,
		ID_TARJA INT,
        ID_SUBSTANCIA INT NOT NULL,
        ID_EMPRESA INT,

		FOREIGN KEY (ID_USUARIO)
		REFERENCES USUARIOS(ID_USUARIO)
		ON DELETE SET NULL,

		FOREIGN KEY (ID_PRESCRICAO)
		REFERENCES PRESCRICOES(ID_PRESCRICAO)
		ON DELETE CASCADE,

		FOREIGN KEY (ID_TARJA)
		REFERENCES TARJAS(ID_TARJA)
		ON DELETE SET NULL,
		
		FOREIGN KEY (ID_SUBSTANCIA)
		REFERENCES SUBSTANCIAS(ID_SUBSTANCIA)
		ON DELETE CASCADE,
        
        FOREIGN KEY (ID_EMPRESA)
		REFERENCES EMPRESAS(ID_EMPRESA)
		ON DELETE SET NULL
	);

CREATE TABLE REMEDIOS_PUBLICO_ALVO
(
    ID_REMEDIO_PUBLICO INT PRIMARY KEY AUTO_INCREMENT NOT NULL,

    ID_REMEDIO INT NOT NULL,
    ID_PUBLICO_ALVO INT NOT NULL,

    UNIQUE (ID_REMEDIO, ID_PUBLICO_ALVO),

    FOREIGN KEY (ID_REMEDIO)
    REFERENCES REMEDIOS(ID_REMEDIO)
    ON DELETE CASCADE,

    FOREIGN KEY (ID_PUBLICO_ALVO)
    REFERENCES PUBLICO_ALVO(ID_PUBLICO_ALVO)
    ON DELETE CASCADE
);

INSERT INTO TARJAS (NOME) VALUES
('Sem tarja'),
('Vermelha'),
('Preta');

INSERT INTO PUBLICO_ALVO (NOME) VALUES
('Crianças'),
('Adultos'),
('Idosos'),
('Gestantes');

INSERT INTO CIDADES (NOME, UF) VALUES
('Londrina', 'PR'), ('São Paulo', 'SP'), ('Curitiba', 'PR'), ('Maringá', 'PR'), ('Cascavel', 'PR'), ('Foz do Iguaçu', 'PR'),
('Porto Alegre', 'RS'),
('Florianópolis', 'SC'),
('Belo Horizonte', 'MG'),
('Rio de Janeiro', 'RJ'),
('Campinas', 'SP'),
('Ribeirão Preto', 'SP'),
('Salvador', 'BA'),
('Recife', 'PE'),
('Brasília', 'DF');

INSERT INTO EMPRESAS (NOME, CNPJ, ID_CIDADE) VALUES 
('Abvvie Farma', '17405230000112', 1),
('Abbott Laboratórios do Brasil', '56998701000116', 1),
('Aché Laboratórios Farmacêuticos', '60659463000191', 1),
('Allergan Produtos Farmacêuticos', '43426535000124', 1),
('Anb Farma', '02518318000170', 5),
('APSEN Farmacêutica', '62462015000129', 1),
('Arte Nativa Produtos Naturais', '00627715000107', 3),
('Aspen Pharma Brasil', '02500581000160', 2),
('Aspen Pharmacare', '02500581000240', 2),
('AstraZeneca do Brasil', '60318797000100', 1),
('B. Braun S/A', '31673254000102', 2),
('Baldacci S/A', '61150447000131', 1),
('Bayer S/A', '18459628000115', 1),
('Biolab Sanus Farmacêutica', '49475833000106', 1),
('Biomm S/A', '04764834000153', 3),
('Blau Farmacêutica', '58430828000160', 1),
('Boehringer Ingelheim', '60831658000177', 1),
('Brainfarma Indústria Química', '05342417000152', 4),
('Bristol-Myers Squibb', '56998982000107', 1),
('Broker Comércio de Medicamentos', '05414757000101', 3),
('Cellera Farma', '11831154000193', 1),
('Catarinense Nutrição', '84684844000131', 7),
('Chiesi Farmacêutica', '61363032000146', 1),
('Cimed Indústria de Medicamentos', '02814496000190', 3),
('Clamed (Drogaria Catarinense)', '84683549000101', 7),
('Cristália Produtos Químicos Farmacêuticos', '44734671000151', 1),
('Diffucap Chemobras', '33141748000108', 2),
('Distribuidora Onofre', '61181244000140', 1),
('DPSP (Drogaria SP / Pacheco)', '61437224000106', 1),
('Drogaria Araujo', '17256512000116', 3),
('Drogarias DPSP RJ', '33438250000103', 2),
('EMS S/A', '57507378000101', 1),
('Eli Lilly do Brasil', '43943950000100', 1),
('Equiplex Indústria Farmacêutica', '01784411000102', 4),
('Eurofarma Laboratórios', '61190096000192', 1),
('Extrafarma (Imifarma)', '04899316000120', 9),
('Farmácias Nissei S/A', '13131313131313', 5),
('Fresenius Kabi Brasil', '49322431000100', 1),
('FQM (Farmoquímica)', '33409087000184', 2),
('FQM Melora', '33409087000346', 2),
('Galderma Brasil', '00317372000146', 1),
('Geolab Indústria Farmacêutica', '03485572000135', 4),
('Germed Farmacêutica', '04257060000141', 1),
('Glenmark Farmacêutica', '04712411000196', 1),
('Globo Formas Farmacêuticas', '17165234000144', 3),
('GlaxoSmithKline (GSK)', '33247743000110', 2),
('Gross Laboratórios', '33114778000125', 2),
('Halexistar Indústria Farmacêutica', '01571702000198', 4),
('Hebron Farmacêutica', '11954767000120', 8),
('Herbarium Laboratório Botânico', '78950011000120', 5),
('Hypera Pharma (Brainfarma)', '02932074000191', 4),
('Hypofarma', '17176140000123', 3),
('Isofarma Industrial Farmacêutica', '02237582000160', 9),
('Janssen-Cilag', '51780468000187', 1),
('Johnson & Johnson do Brasil', '54516661000101', 1),
('Kley Hertz Farmacêutica', '92695634000113', 6),
('Laboratório Catarinense S/A', '84684844000120', 7),
('Laboratório Daudt', '33025297000103', 2),
('Legrand Medicamentos', '05044784000285', 1),
('Legrand Pharma', '05044784000102', 1),
('Libbs Farmacêutica', '61230314000175', 1),
('Mantecorp Skincare', '61082426000144', 1),
('Marjan Farma', '60726692000181', 1),
('Merck S/A', '33069212000184', 2),
('Momenta Farmacêutica', '15224351000171', 1),
('Multilab Indústria Farmacêutica', '92283522000104', 6),
('Mylan Laboratórios', '10640473000189', 1),
('Natulab Laboratório', '02456955000183', 10),
('Neo Química', '29785870000103', 4),
('Nikkho do Brasil', '33109315000122', 2),
('Novartis Biociências', '56994502000130', 1),
('Novo Nordisk Farmacêutica', '86643095000106', 3),
('Nova Química', '05045656000151', 1),
('Pague Menos S/A', '06626253000151', 9),
('Panarello Medicamentos', '04225437000138', 4),
('Panvel (Dimed)', '92665611000177', 6),
('Pharma Nostra', '04085440000157', 1),
('Pharlab Indústria Farmacêutica', '02508068000121', 3),
('Pfizer Brasil', '46070868000169', 1),
('Prati Donaduzzi Genéricos', '73856593000247', 5),
('Prati-Donaduzzi', '73856593000166', 5),
('Profarma Distribuidora', '33132044000124', 2),
('Raia Drogasil (RD Saúde)', '61585865000151', 1),
('Roche Farma Brasil', '33009945000123', 2),
('Samtec Biotecnologia', '58178146000150', 1),
('Sanofi Aventis', '38325850000114', 1),
('Sanofi Medley', '10588595000197', 1),
('SantaCruz Distribuidora', '61270385000102', 1),
('Servier do Brasil', '42585190000169', 2),
('Supera Farma', '14157771000112', 1),
('Takeda Distribuidora', '14862571000101', 1),
('Teuto Brasileiro', '17159229000176', 4),
('Theraskin Farmacêutica', '61517397000112', 1),
('União Química Farmacêutica Nacional', '60665981000118', 1),
('Weleda do Brasil', '61066007000124', 1),
('Zambon Laboratórios', '45895422000110', 1),
('Zydus Nikkho', '05255475000180', 2);

-- professor@gmail.com Professor1
INSERT INTO USUARIOS VALUES (1, 'professor@gmail.com','a1935e0d7098121067f002da5f187f73', 'Professor');


SELECT R.*, P.*, T.NOME TARJA, GROUP_CONCAT(PA.NOME SEPARATOR ', ') "Público Alvo", E.NOME EMPRESA, E.CNPJ, C.NOME CIDADE, C.UF, S.NOME SUBSTANCIA, S.TIPO "Tipo da substância" FROM REMEDIOS R, PRESCRICOES P, TARJAS T, REMEDIOS_PUBLICO_ALVO RPA, PUBLICO_ALVO PA, EMPRESAS E, CIDADES C, SUBSTANCIAS S WHERE R.ID_PRESCRICAO = P.ID_PRESCRICAO AND R.ID_TARJA = T.ID_TARJA AND R.ID_REMEDIO = RPA.ID_REMEDIO AND RPA.ID_PUBLICO_ALVO = PA.ID_PUBLICO_ALVO AND R.ID_EMPRESA = E.ID_EMPRESA AND E.ID_CIDADE = C.ID_CIDADE AND R.ID_SUBSTANCIA = S.ID_SUBSTANCIA GROUP BY R.ID_REMEDIO, P.ID_PRESCRICAO, T.NOME, E.NOME, E.CNPJ, C.NOME, C.UF, S.NOME, S.TIPO;

SELECT * FROM USUARIOS