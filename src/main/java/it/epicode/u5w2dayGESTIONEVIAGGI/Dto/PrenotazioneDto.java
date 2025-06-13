package it.epicode.u5w2dayGESTIONEVIAGGI.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Viaggio;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {

    private String preferenze;
    @NotNull(message = "La data della richiesta non può essere nulla")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataRichiesta;
    private int viaggioId;
    private int dipendenteId;
}
