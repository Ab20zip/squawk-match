public class Parrot {
    private int id;
    private String name;
    private String species;
    private int age;
    private String description;

    public Parrot(int id, String name, String species, int age, String description) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Parrot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
