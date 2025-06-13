package it.epicode.u5w2dayGESTIONEVIAGGI.Dto;

import it.epicode.u5w2dayGESTIONEVIAGGI.Enum.StatoViaggio;
import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Dipendente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ViaggioDto {
    @NotEmpty(message = "Il campo destinazione non può essere nullo o vuoto")
    private String destinazione;
    @NotNull(message = "la data del viaggio non può essere nulla")
    private LocalDate dataViaggio;

}
