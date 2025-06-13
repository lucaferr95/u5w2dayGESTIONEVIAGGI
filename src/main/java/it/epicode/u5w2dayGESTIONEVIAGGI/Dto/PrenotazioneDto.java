package it.epicode.u5w2dayGESTIONEVIAGGI.Dto;

import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Viaggio;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class PrenotazioneDto {

    private String preferenze;
    private int viaggioId;
}
