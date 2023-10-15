package olegivanov.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "files")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @Column(nullable = false, unique = true)
    private String filename;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String type;

    @Lob
    private byte[] content;

    @ManyToOne
    private User user;
}
