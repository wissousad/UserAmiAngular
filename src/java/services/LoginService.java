/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Ouissal
 */
public interface LoginService {

    public String partriculierExistGetName(Long id, String Password);

    public String professionnelExistGetName(Long id, String Password);

    public String partriculierGetName(Long id);

    public String professionnelGetName(Long id);

    public String conseillerExistGetName(Long id, String Password);

    public String conseillerGetName(Long id);
    
}
