package odesk.test.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Version {

    @Id
    @Column(name = "VERSION", unique = true, nullable = false)
    private String version;

    private String codeName;

    private String releaseDate;

    private String endOfLife;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Doc> docs = new ArrayList<Doc>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEndOfLife() {
        return endOfLife;
    }

    public void setEndOfLife(String endOfLife) {
        this.endOfLife = endOfLife;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Ubuntu " +
                "version='" + version + '\'' +
                ", code name='" + codeName + '\'' +
                ", release date='" + releaseDate + '\'' +
                ", end of life='" + (endOfLife == null ? "not set" : endOfLife) + '\'' +
                ", documents =" + docs;
    }
}
