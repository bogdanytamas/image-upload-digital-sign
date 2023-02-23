package hu.ponte.hr.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "image_meta")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageMetaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private long size;

    @Lob
    @Column(name = "digital_sign")
    private String digitalSign;

}
