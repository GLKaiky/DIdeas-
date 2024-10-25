--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12 (Ubuntu 14.12-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.12 (Ubuntu 14.12-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: usuario; Type: SCHEMA; Schema: -; Owner: ti2cc
--

CREATE SCHEMA usuario;


ALTER SCHEMA usuario OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: atributo; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.atributo (
    id_atributo integer NOT NULL,
    carisma integer NOT NULL,
    inteligencia integer NOT NULL,
    constituicao integer NOT NULL,
    destreza integer NOT NULL,
    forca integer NOT NULL,
    sabedoria integer NOT NULL
);


ALTER TABLE usuario.atributo OWNER TO ti2cc;

--
-- Name: classe; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.classe (
    nome name NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE usuario.classe OWNER TO ti2cc;

--
-- Name: magia; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.magia (
    nome name NOT NULL,
    nivel integer NOT NULL,
    descricao character varying NOT NULL,
    nome_personagem character varying NOT NULL
);


ALTER TABLE usuario.magia OWNER TO ti2cc;

--
-- Name: personagem; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.personagem (
    nome name NOT NULL,
    descricao character varying,
    religiao character varying,
    historia character varying NOT NULL,
    tendencia character varying NOT NULL,
    usuario_id integer NOT NULL,
    nome_classe character varying NOT NULL,
    nome_raca character varying NOT NULL,
    id_atributo integer NOT NULL,
    img character varying
);


ALTER TABLE usuario.personagem OWNER TO ti2cc;

--
-- Name: raca; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.raca (
    nome name NOT NULL,
    descricao character varying
);


ALTER TABLE usuario.raca OWNER TO ti2cc;

--
-- Name: usuarios; Type: TABLE; Schema: usuario; Owner: ti2cc
--

CREATE TABLE usuario.usuarios (
    id integer NOT NULL,
    username character varying NOT NULL,
    email character varying NOT NULL,
    senha character varying NOT NULL,
    nome name NOT NULL,
    img character varying
);


ALTER TABLE usuario.usuarios OWNER TO ti2cc;

--
-- Name: TABLE usuarios; Type: COMMENT; Schema: usuario; Owner: ti2cc
--

COMMENT ON TABLE usuario.usuarios IS 'Atributos de usuario';


--
-- Data for Name: atributo; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.atributo (id_atributo, carisma, inteligencia, constituicao, destreza, forca, sabedoria) FROM stdin;
312409	0	0	0	0	0	0
604785	0	0	0	0	0	0
832341	0	0	0	0	0	0
519261	0	0	0	0	0	0
832335	0	0	0	0	0	0
769481	6	5	4	3	2	1
803641	1	1	1	1	1	1
509224	1	1	1	1	1	1
334032	1	1	1	1	1	1
837231	7	6	4	3	0	1
576108	2	2	2	2	2	2
926151	5	0	0	3	8	1
715519	3	6	4	5	2	1
17260	3	6	4	5	2	1
57316	10	14	12	16	16	18
492275	200	0	100	1	100	100
403592	10	0	1	1	2	0
768127	10	0	1	1	2	0
500084	10	0	1	1	2	0
381605	10	0	1	1	2	0
568875	10	0	1	1	2	0
104506	4	7	8	9	5	2
178762	1	1	1	1	1	1
583280	3	2	3	6	5	0
\.


--
-- Data for Name: classe; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.classe (nome, descricao) FROM stdin;
Bárbaro	Mestres na arte da brutalidade, os bárbaros são guerreiros ferozes que se lançam no calor da batalha sem medo ou hesitação. Eles canalizam sua raiva para aumentar sua força e resistência, tornando-se verdadeiras forças da natureza em combate.
Bardo	Artistas ambulantes, historiadores e manipuladores mágicos, os bardos encantam e inspiram com suas habilidades versáteis. Eles combinam o poder da música, poesia e magia para influenciar o mundo ao seu redor, tanto em batalha quanto em situações sociais
Clérigo	Devotos de divindades, os clérigos são canalizadores de poder divino. Eles servem como intermediários entre os deuses e o mundo mortal, usando sua fé para curar feridas, banir o mal e lançar poderosas magias divinas
Druida	Guardiões da natureza, os druidas têm uma conexão profunda com o mundo natural e seus segredos. Eles podem assumir formas animais, invocar elementos da natureza e proteger o equilíbrio ecológico com sua magia
Feiticeiro	Herdeiros de poderes mágicos ancestrais, os feiticeiros têm magia em seu sangue. Eles lançam feitiços poderosos com pouca ou nenhuma preparação, canalizando sua energia inata para manipular a realidade ao seu redor
Guerreiro	Mestres da guerra, os guerreiros são combatentes habilidosos que se destacam em combate corpo a corpo. Armados com uma variedade de armas e armaduras, eles são os pilares da força bruta em qualquer grupo de aventureiros
Ladino	Especialistas em furtividade e astúcia, os ladrões são mestres da arte do subterfúgio. Eles se destacam em ataques furtivos, desarmes de armadilhas e desbloqueio de fechaduras, usando sua agilidade e inteligência para superar obstáculos
Mago	Estudiosos dedicados à magia arcana, os magos são mestres da manipulação da energia mágica. Eles estudam feitiços em seus grimórios e podem lançar uma ampla variedade de magias para controlar o fogo, gelo, relâmpagos e muito mais
Paladino	Campeões da justiça e da retidão, os paladinos são guerreiros sagrados investidos com poderes divinos. Eles juram servir a uma causa nobre e lutam contra o mal com ferocidade e convicção, protegendo os inocentes e enfrentando os inimigos da luz
Patrulheiro	Guardiões das fronteiras selvagens, os patrulheiros são exploradores habilidosos e caçadores experientes. Eles dominam as artes da sobrevivência, rastreamento e combate à distância, tornando-se especialistas em lidar com ameaças naturais e sobrenaturais
\.


--
-- Data for Name: magia; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.magia (nome, nivel, descricao, nome_personagem) FROM stdin;
\.


--
-- Data for Name: personagem; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.personagem (nome, descricao, religiao, historia, tendencia, usuario_id, nome_classe, nome_raca, id_atributo, img) FROM stdin;
kaiky	cabelo preto e bigodudo	ateu	nasceu cresceu morreu	Vilao	927107	Bardo	Dragãoborn	926151	\N
jogfador	asdk	sadas	lookd	Vilao	606967	Bárbaro	Anão	837231	https://fotosdeperfil.blob.core.windows.net/fotos/perfil606967.png
Daniel	jogador	gatolico	teste	Heroi	606967	Druida	Halfling	803641	https://fotosdeperfil.blob.core.windows.net/fotos/perfil967809.png
sanrio	fodas	gatolico	gigante pa pogha	Heroi	606967	Clérigo	Halfling	334032	https://fotosdeperfil.blob.core.windows.net/fotos/perfil822327.png
Soks	teste	Mamamia	kogador	Neutro	606967	Bardo	Humano	715519	https://fotosdeperfil.blob.core.windows.net/fotos/perfil606967.png
Kaim	Kaim é um usuario de foice extremamente habilidoso, criado por Zed dentro da liga das sombras, sendo seu pupilo e futuro sucessor, busca dominação global	Sombras	dasdds	Vilao	606967	Guerreiro	Humano	576108	https://fotosdeperfil.blob.core.windows.net/fotos/perfil831975.png
balsonaro	gordo feio	ateu	nasceu virou militar e presidente gripezinha	Vilao	487444	Bárbaro	Anão	403592	https://fotosdeperfil.blob.core.windows.net/fotos/perfil531596.png
Feiticeiro	Teste	asdasdd	saddmasdkk	Vilao	606967	Feiticeiro	Humano	104506	https://fotosdeperfil.blob.core.windows.net/fotos/perfil813917.png
Thalgarth, o Guardião das Sombras	Thalgarth é um guerreiro imponente, com uma estatura alta e musculosa. Seus olhos, brilhantes como brasas em uma noite escura, refletem uma determinação feroz. Seu cabelo negro como ébano é longo e geralmente está amarrado em uma trança, enquanto uma barba espessa adorna seu rosto marcado por batalhas.	Thalgarth venera os antigos deuses das sombras, deidades que representam a dualidade entre luz e escuridão. Ele presta homenagem aos deuses oferecendo orações silenciosas nas noites de lua cheia, quando as sombras são mais profundas	Thalgarth nasceu nas profundezas das terras sombrias do Reino de Erethor, um lugar onde as sombras dançam entre as árvores antigas e segredos ancestrais residem nas montanhas. Desde jovem, ele foi treinado pelos guardiões das trevas, uma ordem de guerreiros que protege as fronteiras do reino contra as criaturas sinistras que espreitam nas sombras. Sua vida foi marcada por inúmeras batalhas contra as forças das trevas, e ele ganhou reputação como um defensor implacável dos inocentes.	Neutro	606967	Guerreiro	Meio-Elfo	57316	https://fotosdeperfil.blob.core.windows.net/fotos/perfil582964.png
robin	robin	robin	robin	Heroi	33798	Guerreiro	Meio-Elfo	492275	\N
testeBruxo	asda	gatolico	asdasd	Heroi	606967	Mago	Anão	178762	\N
testePaladino	asdskm	gatolico	aksdka	Neutro	606967	Paladino	Dragãoborn	583280	\N
\.


--
-- Data for Name: raca; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.raca (nome, descricao) FROM stdin;
Humano	Humanos são uma das raças mais versáteis e prolíficas em D&D. Eles não têm habilidades raciais especiais inatas, mas sua adaptabilidade e diversidade cultural os tornam excelentes em uma variedade de classes e profissões. Eles são conhecidos por sua ambição, coragem e capacidade de se adaptar a diferentes situações
Anão	Anões são uma raça resistente e trabalhadora, conhecida por sua força, resistência e habilidade em trabalhar com metais e pedras. Eles são geralmente associados a montanhas e cavernas, onde constroem grandes cidades e fortalezas. Anões valorizam a honra, a tradição e a lealdade à sua família e comunidade
Elfo	 Elfos são uma raça graciosa e mística, conhecida por sua beleza, longevidade e afinidade com a natureza. Eles são ágeis e ágeis, com sentidos aguçados e uma conexão profunda com o mundo natural. Elfos são muitas vezes retratados como artistas, poetas e guardiões das florestas
Halfling	Halflings, também conhecidos como hobbites, são uma raça pequena e amigável, conhecida por sua natureza alegre, habilidade em furtividade e amor pela boa comida. Eles são ágeis e furtivos, excelentes em se esgueirar e escapar de situações perigosas. Halflings valorizam a paz, a harmonia e o conforto do lar
Meio-Elfo	Meio-elfos são uma mistura das características dos humanos e elfos, combinando a versatilidade e adaptabilidade dos humanos com a graça e os sentidos aguçados dos elfos. Eles muitas vezes enfrentam o desafio de pertencer a duas culturas diferentes, mas também têm a capacidade de agir como mediadores e pontes entre raças
Meio-Orc	Meio-orcs são o resultado do cruzamento entre humanos e orcs, combinando a força bruta e a ferocidade dos orcs com a determinação e a tenacidade dos humanos. Eles são frequentemente retratados como guerreiros ferozes e brutais, mas também podem ser leais e protetores com aqueles que consideram amigos ou aliados
Gnomo	Gnomos são uma raça pequena e curiosa, conhecida por sua engenhosidade, amor pela magia e fascínio por máquinas e engenhocas. Eles são inventores talentosos e alquimistas habilidosos, sempre buscando novas formas de explorar e entender o mundo ao seu redor
Dragãoborn	Dragãoborns são descendentes de dragões ancestrais, com características semelhantes às dessas criaturas lendárias. Eles possuem escalas, garras e, às vezes, até asas, além de habilidades especiais baseadas no tipo de dragão de sua linhagem. Dragãoborns são frequentemente orgulhosos, corajosos e dedicados a honrar a memória de seus ancestrais
Tiefling	Tieflings têm sangue demoníaco em suas veias, conferindo-lhes características infernais como chifres, caudas e olhos brilhantes. Eles muitas vezes enfrentam o preconceito e a desconfiança devido às suas origens, mas também podem usar seus poderes infernais para grandes feitos e aventuras épicas
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: usuario; Owner: ti2cc
--

COPY usuario.usuarios (id, username, email, senha, nome, img) FROM stdin;
596526	asdsa	kaikyfranca8@gmail.com	asdasd	asds	\N
494126	kdkdkd	kaikyfranca18@gmail.com	asdas	assd	\N
797126	monica	kaikyfranca10@gmail.com	kaka	Eduardo	\N
987452	vivane37	vivianefranca37@yahoo.com	1234	37vivane	\N
708799	mmmm	mmmm@gmail.com	mmmmm	mmmm	\N
251096	kkk	kkk@gmail.com	sddd	kkk	\N
562448	teste3	teste3@gmail.com	12345	viviane franca	\N
721496	daniel	daniel.80@gmail.com	eduardo123	roberto	\N
206707	sdfds	sdfsdfsdf@dfsdf	6fbfd5e68d3306e51350bea0232f8fa5	dsdcffds	\N
33798	xX_albionIV_Xx	aguinaldo890@uorak.com	e8d95a51f3af4a3b134bf6bb680a213a	albedo junior quarto civil	https://fotosdeperfil.blob.core.windows.net/blob/perfil33798.png
187996	laviniafranca	batata@gmail.com	123789	Lavinia Franca	\N
606967	THE MONITOR	kaikyfrancapro8@gmail.com	48eef046080aa60b421d992d5819c129	Kaiky França da Silva	https://fotosdeperfil.blob.core.windows.net/blob/perfil606967.png
663918	teste5	teste5@gmail.com	48eef046080aa60b421d992d5819c129	teste5	\N
260008	teste8	teste8@gmail.com	f6fd1939bdf31481d27ac4344a2aab58	teste8	\N
487444	luizinacio	luizinaciolula@gmail.com	892dd584f5e7c10c54c7a8a4a01b841f	luiz inacio	https://fotosdeperfil.blob.core.windows.net/blob/perfil487444.png
522264	teste	teste@gmail.com	kakavivi	teste	https://fotosdeperfil.blob.core.windows.net/blob/perfil522264.png
927107	cassio123454	laviniafrancas19@gmail.com	kaikyfeio	Cássio Luiz	https://fotosdeperfil.blob.core.windows.net/blob/perfil927107.png
\.


--
-- Name: atributo Atributo_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.atributo
    ADD CONSTRAINT "Atributo_pkey" PRIMARY KEY (id_atributo);


--
-- Name: classe Classe_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.classe
    ADD CONSTRAINT "Classe_pkey" PRIMARY KEY (nome);


--
-- Name: magia Magia_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.magia
    ADD CONSTRAINT "Magia_pkey" PRIMARY KEY (nome);


--
-- Name: personagem Personagem_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.personagem
    ADD CONSTRAINT "Personagem_pkey" PRIMARY KEY (nome);


--
-- Name: raca Raça_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.raca
    ADD CONSTRAINT "Raça_pkey" PRIMARY KEY (nome);


--
-- Name: usuarios Usuário_Username_key; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.usuarios
    ADD CONSTRAINT "Usuário_Username_key" UNIQUE (username);


--
-- Name: usuarios Usuário_pkey; Type: CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.usuarios
    ADD CONSTRAINT "Usuário_pkey" PRIMARY KEY (id);


--
-- Name: magia Magia_nome_personagem_fkey; Type: FK CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.magia
    ADD CONSTRAINT "Magia_nome_personagem_fkey" FOREIGN KEY (nome_personagem) REFERENCES usuario.personagem(nome);


--
-- Name: personagem Personagem_id_atributo_fkey; Type: FK CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.personagem
    ADD CONSTRAINT "Personagem_id_atributo_fkey" FOREIGN KEY (id_atributo) REFERENCES usuario.atributo(id_atributo);


--
-- Name: personagem Personagem_nome_classe_fkey; Type: FK CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.personagem
    ADD CONSTRAINT "Personagem_nome_classe_fkey" FOREIGN KEY (nome_classe) REFERENCES usuario.classe(nome);


--
-- Name: personagem Personagem_nome_raca_fkey; Type: FK CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.personagem
    ADD CONSTRAINT "Personagem_nome_raca_fkey" FOREIGN KEY (nome_raca) REFERENCES usuario.raca(nome);


--
-- Name: personagem Personagem_usuario_id_fkey; Type: FK CONSTRAINT; Schema: usuario; Owner: ti2cc
--

ALTER TABLE ONLY usuario.personagem
    ADD CONSTRAINT "Personagem_usuario_id_fkey" FOREIGN KEY (usuario_id) REFERENCES usuario.usuarios(id);


--
-- Name: SCHEMA usuario; Type: ACL; Schema: -; Owner: ti2cc
--

GRANT ALL ON SCHEMA usuario TO PUBLIC;


--
-- Name: TABLE usuarios; Type: ACL; Schema: usuario; Owner: ti2cc
--

REVOKE ALL ON TABLE usuario.usuarios FROM ti2cc;
GRANT ALL ON TABLE usuario.usuarios TO ti2cc WITH GRANT OPTION;
GRANT ALL ON TABLE usuario.usuarios TO pg_execute_server_program WITH GRANT OPTION;
GRANT DELETE ON TABLE usuario.usuarios TO PUBLIC;


--
-- PostgreSQL database dump complete
--

