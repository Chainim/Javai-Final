- Interação (possivel interface grafica ou interface em texto e como o jogador dá comandos para o jogo)
- Funcionalidade cartas
- Logica basica jogo

- Criar uma classe de modelo para cartas
- Usar state pattern no game loop

# Game Flow
## Game Init
- Criar deques dos dois players (até 40 cartas)
- Randomizar as cartas dos deques dos players
- Perguntar por cartas substituidas (0 a 4 cartas). A informação das cartas só fica disponível após confirmação


## Game Loop
- Adiciona quantidade de mana referente à rodada atual
- Puxar uma carta do deque
- Invoca algumas cartas escolhidas, de acordo com a mana
- Efeito das cartas sumonadas é aplicado
- Combate
	- Jogador da vez seleciona as cartas de ataque
	- Jogador adversario seleciona as cartas de defesa 
	- Verifica se o campeão subiu de nível
- Guarda a mana que sobrou (num máximo de 3 de mana) para realizar feitiços na próxima rodada
- Efeito das cartas do adversário é ativado

# Funcionalidades

1.  [ ] Embaralhar baralho
2.  [ ] Distribuir cartas e fazer substituição
3.  [ ] Puxar uma carta no inicio de cada rodada
4.  [ ] Rodadas
5.  [ ] Invocar cartas
6.  [ ] Substituir cartas
7.  [ ] Atacar
8.  [ ] Defender
9.  [ ] Fim da rodada
10. [ ] Mana e "mana de feitiço"
11. [ ] Combate
12. [ ] Cartas
13. [ ] [Traços](#Traços)
14. [ ] [Efeitos](#Efeitos)
15. [ ] Jogar contra o computador
16. [ ] Mostrar detalhes da situação do jogo
17. [ ] Gerenciar decks
18. [ ] [Deck base](#Deck-base)

- [ ] Relatorio

# Deck base
- Campeões
	- [ ] Garren
- Seguidores
	- [ ] Tiana
	- [ ] Vanguarda
	- [ ] Duelista
	- [ ] Defensor
	- [ ] Poro
	- [ ] Poro defensor
- Feitiços
	- [ ] Julgamento
	- [ ] Valor redobrado
	- [ ] Golpe certeiro
	- [ ] Combate um-a-um

# Traços
- [ ] Ataque duplo
- [ ] Elusivo
- [ ] Fúria

# Efeitos
- [ ] Dê +n/ + m a todas as unidades aliadas evocadas.
- [ ] Dê +n/ + m a uma unidade aliada nesta rodada.
- [ ] Se a carta destruir uma unidade do inimigo nesta rodada, é colocada uma nova carta de uma unidade especifico na sua mão.
- [ ] Cure inteiramente uma unidade aliada.
- [ ] Dobre o ataque e defesa de uma unidade aliada.
- [ ] Escolha um aliado e um oponente para um combate imediato.
- [ ] Uma unidade evocada ataca o Nexus do adversário.
- [ ] Um aliado atacante golpeia todos os oponentes defensores.
- [ ] Ao ser destruído, voce ganha uma carta.
- [ ] Altera o poder de uma unidade para 0 nesta rodada.
- [ ] Cria uma barreira que anula o proximo dano que uma unidade aliada levaria. Dura uma rodada.
- [ ] Golpeia o Nexus do adversario para n pontos de dano.
