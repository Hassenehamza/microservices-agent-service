package com.example.agentservice.service;



import com.example.agentservice.dto.Client;
import com.example.agentservice.entity.Agent;
import com.example.agentservice.repository.AgentRepository;
import com.example.agentservice.security.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgentService(AgentRepository agentRepository, PasswordEncoder passwordEncoder) {
        this.agentRepository = agentRepository;


        this.passwordEncoder = passwordEncoder;
    }

/*    public String changePassword(String newPassword)
    {
        String currentLoggedInUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Agent agent = agentRepository.findAgentByUsername(currentLoggedInUser).get();

        if(!agent.isPasswordChanged())
        {
            agentRepository.changePasswordInFirstLogin(passwordEncoder.encode(newPassword), true, agent.getId());
            System.out.println();
            return "password changed successfully";
        }
        System.out.println("password changed successfully");
        return "password changed successfully";


    }*/


    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

/*    public List<Client> getClients()
    {
        String currentLoggedInUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentAgentId = agentRepository.findAgentByUsername(currentLoggedInUser).get().getId();

        return .getRelatedClients(currentAgentId);

    }*/

/*    public void addClient(Client client,String loginLink)
    {
        String currentLoggedInUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Agent me = agentRepository.findAgentByUsername(currentLoggedInUser).get();
        client.setCreatedBy(me);
        // thank u nizar 2
        Optional<Client> clientByUsername = clientRepository.findClientByUsername(client.getUsername());
        if(clientByUsername.isPresent())
        {
            throw new IllegalStateException("client Already Exists");
        }
        String pass = PasswordGenerator.alphaNumericString();
        System.out.println(pass);
        client.setPhone("+212"+client.getPhone());
        //Send Password to agent by email
        client.setUsername(client.getPhone());
        emailService.sendEmailWithTemplating(client.getEmail(),client.getUsername(),pass,client.getFirstName(),client.getLastName(),loginLink);
        client.setRole("CLIENT");
        client.setPassword(passwordEncoder.encode(pass));
        clientRepository.save(client);
    }*/

/*    public List<Demande> geClientsDemandes()
    {

        return this.demandeRepository.findAll();
    }*/

/*    public void approveDemande(Long demandeId,Boolean status)
    {
        if(status)
        {
            Client client = new Client(this.demandeRepository.findById(demandeId).get());
            client.setPassword(passwordEncoder.encode("motdepasse"));
            String currentLoggedInUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Agent me = agentRepository.findAgentByUsername(currentLoggedInUser).get();
            client.setCreatedBy(me);
            this.clientRepository.save(client);
        }

        this.demandeRepository.deleteById(demandeId);
    }*/

    public void addAgent(Agent agent, String loginLink) {
        String pass = PasswordGenerator.alphaNumericString();
        System.out.println(pass);
        //Send Password to agent by email
        agent.setPassword(passwordEncoder.encode(pass));
        this.agentRepository.save(agent);
    }
}
