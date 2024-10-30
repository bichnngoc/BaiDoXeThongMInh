package com.example.baidoxe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ThongTinDo")
public class ThongTinDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private LocalDate ThoiGianVao;
    private Integer Status;

    @ManyToOne
    @JoinColumn(name = "DatCho_Id", referencedColumnName = "Id")
    private DatCho datCho;
}
