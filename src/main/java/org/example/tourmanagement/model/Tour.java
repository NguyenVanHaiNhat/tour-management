package org.example.tourmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String destination;
    private double price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
