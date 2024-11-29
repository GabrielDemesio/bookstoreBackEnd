package projectbookstore.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;

@Validated
@Data
@Entity
@Getter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message = "Title must be defined")
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @NotBlank(message = "Author must be defined")
    @Column(nullable = false, name = "author")
    private String author;

    @NotNull
    @NotBlank(message = "Text must be defined")
    @Column(nullable = false, name = "text")
    private String text;

}
