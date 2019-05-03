package correios.natal.adoteumacarta;

public class Doador {

    private String nome;
    private String cell;
    private String gift;
    private String status;

    public Doador(String nome, String cell, String gift, String status){
        this.nome = nome;
        this.cell = cell;
        this.gift = gift;
        this.status = status;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
