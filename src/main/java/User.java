public class User {
    private Long id;
    private String name;
    private String city;
    private String languageProgramming;

    public User(Long id, String name, String city, String languageProgramming) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.languageProgramming = languageProgramming;
    }

    public String getLanguageProgramming() {
        return languageProgramming;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", languageProgramming='" + languageProgramming + '\'' +
                '}';
    }
}
