/**
 * Created by mario on 18/07/17.
 */
class Pokemon(val nome: String,var hp: Int,var ataque: Int,var defesa: Int,val ataques: Array<Ataque>){
    fun obterAtaque(ordem: Int) = if(ordem > ataques.size) ataques.get(ordem) else ataques.get(0)
}

class Ataque(val nome: String, var poder: Int)
