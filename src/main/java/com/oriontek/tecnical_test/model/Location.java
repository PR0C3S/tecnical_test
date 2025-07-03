    package com.oriontek.tecnical_test.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.oriontek.tecnical_test.enums.TypeHouse;
    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "location")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(exclude = {"customer"})
    @ToString(exclude = {"customer"})
    public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private City city;

        @ManyToOne
        @JoinColumn(name="customer_id", nullable=false)
        @JsonBackReference
        private Customer customer;

        @Column(nullable = false)
        private String zone;

        @Column(nullable = false)
        private String street;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private TypeHouse typeOfHouse;

        @Column(nullable = true)
        private String houseNumber;

        @Column(nullable = true)
        private String buildingName;

        @Column(nullable = true)
        private String unitNumber;
    }
