package settings;

import java.util.Date;

public class Config {
    private String SYSTEM_VERSION = "v0.1";
    private String SUPERMERCADO_NOME = "Supermercado Juninho";

    public String getVersion() {
        return SYSTEM_VERSION;
    }

    public String getNome() {
        return SUPERMERCADO_NOME;
    }

    public void limparTela() {
        for(int i = 0; i < 20; i++) {
            System.out.println(" ");
        }
    }

    public String retornarTempo() {
        Date data = new Date();
        if(data.getHours() >= 12) {
            return "Boa tarde";
        } else if(data.getHours() >= 18) {
            return "Boa noite";
        } else if(data.getHours() >= 0) {
            return "Bom dia";
        }
        return null;
    }
}
