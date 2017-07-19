import java.util.*

/**
 * Created by mario on 18/07/17.
 */
enum class Pokemons(val id: Int){
    BULBASAUR(1),
    CHARMANDER(2),
    SQUINTLE(3),
    PIKACHU(4)
}

fun main(args: Array<String>) {
    println("Escolha um Pokemon:")

    for ((index,p) in Pokemons.values().withIndex()){
        println("${index+1} $p")
    }

    val scanner = Scanner(System.`in`)
    val opcao = scanner.nextInt()

    val pokemon: Pokemon = gerarPokemon(opcao)

    mostrarDados(pokemon)

    val random = Random()
    val numeroGerado = 1 + random.nextInt(4)
    val pokemonSelvagem = gerarPokemon(numeroGerado)

    println("Um ${pokemonSelvagem.nome} selvagem apareceu!")

    do {
        println("${pokemon.nome} HP: ${pokemon.hp} | ${pokemonSelvagem.nome} HP: ${pokemonSelvagem.hp}")
        println("Escolha um ataque do Pokemon:")

        for((index,ataque) in pokemon.ataques.withIndex()){
            println("$index : ${ataque.nome}")
        }

        val ataqueSelecionado = scanner.nextInt()

        if(processaAtaque(pokemon,pokemonSelvagem,ataqueSelecionado)){
            break
        }

        val ataqueAleatorio : Int = 1 + random.nextInt(pokemonSelvagem.ataques.size)

        if(processaAtaque(pokemonSelvagem,pokemon,ataqueAleatorio)){
            break
        }else{
            println("Os dois Pokémones ainda de pé!")
            println("Continue....")
        }



    }while (pokemon.hp > 0 && pokemonSelvagem.hp > 0)
}

fun processaAtaque(pokemonAtacante: Pokemon, pokemonDefenor: Pokemon, ataqueSelecionado: Int): Boolean {
    val ataque = pokemonAtacante.obterAtaque(ataqueSelecionado)

    println("${pokemonAtacante.nome} está usando ${ataque.nome}")

    val valorDano = calcularDano(pokemonAtacante.ataque,pokemonAtacante.defesa,ataque)
    println("${pokemonDefenor.nome} está recebendo $valorDano pontos de Dano!")

    pokemonDefenor.hp -= valorDano

    if(pokemonDefenor.hp <= 0) {
        println("${pokemonDefenor.nome} Morreu!")
        println("${pokemonAtacante.nome} venceu a batalha!")
        return true
    }
    return false
}

fun calcularDano(valorAtaque: Int, valorDefesa: Int, ataque: Ataque): Int = ((((2 * 1 + 10.0) / 250) * (valorAtaque / valorDefesa) * ataque.poder  + 2) * 1.5).toInt()

fun mostrarDados(pokemon: Pokemon) {
    println("Você escolheu um ${pokemon.nome} \n " +
            " HP:${pokemon.hp}\n" +
            " Ataque:${pokemon.ataque}\n"+
            " Defesa:${pokemon.defesa}\n"
    )
}

fun gerarPokemon(opcao: Int): Pokemon = when (opcao){
    Pokemons.BULBASAUR.id -> Pokemon("Bulbasaur",45,48,50, arrayOf(Ataque("vine whip",45),Ataque("tackle",40)))
    Pokemons.CHARMANDER.id -> Pokemon("Charmander",30,60,30, arrayOf(Ataque("scratch",40),Ataque("ember",40)))
    Pokemons.SQUINTLE.id -> Pokemon("Squintle",45,80,20, arrayOf(Ataque("tackle",30),Ataque("watergun",50)))
    Pokemons.PIKACHU.id -> Pokemon("Pikachu",100,90,80, arrayOf(Ataque("thunder shock",45),Ataque("quick atack",50)))
    else -> Pokemon("Missingno",33,136,30, arrayOf(Ataque("pay day",25),Ataque("blind",15)))
}
