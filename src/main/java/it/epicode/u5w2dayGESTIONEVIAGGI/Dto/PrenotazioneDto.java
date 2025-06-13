package it.epicode.u5w2dayGESTIONEVIAGGI.Dto;

import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Viaggio;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {

    private String preferenze;
    @NotNull(message = "La data della richiesta non può essere nulla")
    private LocalDate dataRichiesta;
    private int viaggioId;
    private int dipendenteId;
}
