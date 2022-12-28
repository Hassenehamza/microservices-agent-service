package com.example.agentservice.controller;


import com.example.agentservice.dto.Client;
import com.example.agentservice.entity.Agent;
import com.example.agentservice.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AgentController
{
    private final AgentService agentService;
    private final RestTemplate restTemplate;

    @Autowired
    public AgentController(AgentService agentService, RestTemplate restTemplate)
    {
        this.agentService = agentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENT')")
    //@PreAuthorize("hasAuthority('agent:read')")
    public List<Agent> getAgents()
    {
        return agentService.getAgents();
    }

    @PostMapping(value = "/add-agent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addAgent(@RequestBody Agent agent,@RequestParam(value = "login-link") String loginLink)
    {
        agentService.addAgent(agent,loginLink);
        System.out.println("Agent added successfully");
    }

    @PostMapping(value = "/add-client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addClient(@RequestBody Client client)
    {
        restTemplate.postForObject("http://agent-service/add-agent",client,Client.class);
        System.out.println("client added successfully");
    }
}