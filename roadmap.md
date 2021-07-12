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

## Responsabilidade de classes
1. InitState
	- Dá as 4 cartas iniciais e pergunta por substituição de cartas.
2. SummonState
	- Adiciona quantidade de mana referente à rodada atual
	- Puxa uma carta da pilha
	- Invocar criaturas na mão, desde que o custo de mana seja respeitado
	- Jogar um feitiço
2. AttackState
	- O jogador da vez escolhe quais cartas do campo vão atacar
3. DefendState
	- O jogador defensor escolhe quais cartas do campo defendem e o pareamento com as cartas de ataque
4. RoundEndState
	- O dano é aplicado ao jogador defensor
	- Os efeitos de fim de rodada do oponente são aplicados
	- Checa se os campeões evoluiram
	- Atualiza mana de feitiço

# Funcionalidades

1.  [x] Embaralhar baralho
2.  [x] Distribuir cartas e fazer substituição
3.  [x] Puxar uma carta no inicio de cada rodada
4.  [x] Rodadas
5.  [x] Invocar cartas
6.  [x] Substituir cartas
7.  [x] Atacar
8.  [x] Defender
9.  [x] Fim da rodada
10. [x] Mana e "mana de feitiço"
11. [x] Combate
12. [ ] Cartas
13. [ ] [Traços](#Traços)
14. [ ] [Efeitos](#Efeitos)
15. [x] Jogar contra o computador
16. [ ] Mostrar detalhes da situação do jogo
17. [ ] Gerenciar decks
18. [ ] [Deck base](#Deck-base)

- [ ] Relatorio
- [ ] Diagrama UML

# Deck base
- Campeões
	- [x] Garren
- Seguidores
	- [x] Tiana
	- [x] Vanguarda
	- [x] Duelista
	- [x] Defensor
	- [x] Poro
	- [x] Poro defensor
- Feitiços
	- [x] Julgamento
	- [x] Valor redobrado
	- [x] Golpe certeiro
	- [x] Combate um-a-um

# Traços
- [x] Ataque duplo
- [x] Elusivo
- [x] Fúria

# Efeitos
- [x] Dê +n/ + m a todas as unidades aliadas evocadas.
- [x] Dê +n/ + m a uma unidade aliada nesta rodada.
- [x] Se a carta destruir uma unidade do inimigo nesta rodada, é colocada uma nova carta de uma unidade especifico na sua mão.
- [x] Cure inteiramente uma unidade aliada.
- [x] Dobre o ataque e defesa de uma unidade aliada.
- [x] Escolha um aliado e um oponente para um combate imediato.
- [x] Uma unidade evocada ataca o Nexus do adversário.
- [x] Um aliado atacante golpeia todos os oponentes defensores.
- [x] Ao ser destruído, voce ganha uma carta.
- [?] Altera o poder de uma unidade para 0 nesta rodada.
- [?] Cria uma barreira que anula o proximo dano que uma unidade aliada levaria. Dura uma rodada.
- [?] Golpeia o Nexus do adversario para n pontos de dano.
