package it.epicode.u5w2dayGESTIONEVIAGGI.Service;

import com.cloudinary.Cloudinary;
import it.epicode.u5w2dayGESTIONEVIAGGI.Dto.PrenotazioneDto;
import it.epicode.u5w2dayGESTIONEVIAGGI.Exception.NotFoundException;
import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Prenotazione;
import it.epicode.u5w2dayGESTIONEVIAGGI.Model.Viaggio;
import it.epicode.u5w2dayGESTIONEVIAGGI.Repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    //metodo save
    public Prenotazione savePrenotazione(PrenotazioneDto prenotazioneDto) throws NotFoundException {
        Viaggio viaggio = viaggioService.getViaggio(prenotazioneDto.getViaggioId());

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataRichiesta(prenotazioneDto.getDataRichiesta());
        prenotazione.setViaggio(viaggio);
        sendMail("lucaferr95@gmail.com");
        return prenotazioneRepository.save(prenotazione);
    }

    //metodo get prenotazioni
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    //metodo get singola prenotazione
    public Prenotazione getPrenotazione(int id) throws NotFoundException {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con id " + id + " non trovata"));
    }

    //metodo update Prenotazione
    public Prenotazione updatePrenotazione(int id, PrenotazioneDto prenotazioneDto) throws NotFoundException {

        Prenotazione prenotazioneDaAggiornare = getPrenotazione(id);
        prenotazioneDaAggiornare.setDataRichiesta(prenotazioneDto.getDataRichiesta());

          /*
        nel dto io ho anche viaggioId, quindi devo verificare se l'id del viaggio collegato alla prenotazione
        sul db è diverso dall'id dell'università nel dto.
         */

        if (prenotazioneDaAggiornare.getViaggio().getId() != prenotazioneDto.getViaggioId()) {
            Viaggio viaggio = viaggioService.getViaggio(prenotazioneDto.getViaggioId());
            prenotazioneDaAggiornare.setViaggio(viaggio);
        }

        return prenotazioneRepository.save(prenotazioneDaAggiornare);
    }

    //delete prenotazioni
    public void deletePrenotazione(int id) throws NotFoundException {
        Prenotazione prenotazioneDaCancellare = getPrenotazione(id);
        prenotazioneRepository.delete(prenotazioneDaCancellare);
    }

    //invio email
    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}
