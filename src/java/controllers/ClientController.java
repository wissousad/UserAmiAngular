/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CompteEntity;
import dao.ParticulierDAO;
import dao.ParticulierEntity;
import dao.ProfessionnelEntity;
import dao.TransactionEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import services.LoginService;
import services.LoadInfoService;
import services.VirementService;

/**
 *
 * @author Ouissal
 */
@Controller
public class ClientController {

    // Services
    @Autowired
    LoginService loginService;
    @Autowired
    LoadInfoService loadInfoService;
    @Autowired
    VirementService virementService;

    //DAO 
    @Autowired
    private ParticulierDAO particulierDAO;

    public void setParticulierDAO(ParticulierDAO particulierDAO) {
        this.particulierDAO = particulierDAO;
    }


    
    //session
    static HttpSession session;

    private JSONObject requestToJSONObj(HttpServletRequest request) throws JSONException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        try {
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("requete: " + sb.toString());
        JSONObject jObj = new JSONObject(sb.toString());
        return jObj;
    }

    @RequestMapping(value = "getClient", method = RequestMethod.POST)
    public ResponseEntity<?> getClient(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        System.out.println("********************************************* I'm alive  ****************************");
        Logger logger = Logger.getLogger(getClass().getName());
        logger.fine("********************************************* I'm alive  ****************************");
        JSONObject jObj = requestToJSONObj(request);
        Long numero_compte = Long.valueOf(jObj.getString("numero_compte"));
        //String password = jObj.getString("password");
        System.out.println("********************************************* I'm alive  ****************************");
        ParticulierEntity particulier = particulierDAO.find(numero_compte);
        if (particulier !=null) {
            String clientResponse = particulier.loginPassToJSON();
            System.out.println("********************************************* true  user ****************************");
            return new ResponseEntity(clientResponse, HttpStatus.OK);
        } else {
            System.out.println("********************************************* false  user ****************************");
        }
        return new ResponseEntity("[]", HttpStatus.OK);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<?> getUser1(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
//
//        JSONObject jObj = requestToJSONObj(request);
//        Long numero_compte = Long.valueOf(jObj.getString("numero_compte"));
//        String password = jObj.getString("password");
//
//        if (loginService.partriculierExistGetName(numero_compte, password) != null) {
//            String userResponse = particulierEntity.loginPassToJSON();
//            System.out.println("********************************************* true  user ****************************");
//            return new ResponseEntity(userResponse, HttpStatus.OK);
//        } else {
//            System.out.println("********************************************* false  user ****************************");
//
//        }
//        return new ResponseEntity("[]", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public ResponseEntity<?> getUser2(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
//
//        JSONObject jObj = requestToJSONObj(request);
//        Long numero_compte = Long.valueOf(jObj.getString("numero_compte"));
//        String password = jObj.getString("password");
//
//        if (loginService.partriculierExistGetName(numero_compte, password) != null) {
//            String userResponse = particulierEntity.loginPassToJSON();
//            System.out.println("********************************************* true  user ****************************");
//            return new ResponseEntity(userResponse, HttpStatus.OK);
//        } else {
//            System.out.println("********************************************* false  user ****************************");
//
//        }
//        return new ResponseEntity("[]", HttpStatus.OK);
//    }
// public ParticulierEntity JSONObjToUserMessage(JSONObject jObj) throws JSONException
//       {
//            String numero_compte = jObj.getString("numero_compte");
//            String password = jObj.getString ("password");
//            String nom = jObj.getString("nom");
//            UserMessage u = new UserMessage(login,password,firstName,lastName);
//            return u;
//        }
//        
//    @RequestMapping(value = "index", method = RequestMethod.GET)
//    public ModelAndView initIndex() {
//        ModelAndView mv;
//        if (session != null && session.getAttribute("idUser") != null) {
//            mv = new ModelAndView("index");
//            mv.addObject("state", "connected");
//            ModelMap m = mv.getModelMap();
//            Long id = (Long) session.getAttribute("idUser");
//            if (session.getAttribute("typeUser").equals("particulier")) {
//                String name = "Bonjour " + loginService.partriculierGetName(id);
//                m.put("name", name);
//
//            } else if (session.getAttribute("typeUser").equals("professionnel")) {
//                String name = "Bonjour " + loginService.professionnelGetName(id);
//                m.put("name", name);
//            } else {
//                String name = "Bonjour " + loginService.conseillerGetName(id);
//                m.put("name", name);
//            }
//            return mv;
//        } else {
//            mv = new ModelAndView("index");
//            mv.addObject("state", "disconnected");
//        }
//        return mv;
//    }
//
//    @RequestMapping(value = "erreur", method = RequestMethod.GET)
//    public String initErreur() {
//        return "erreur";
//    }
//
//    @RequestMapping(value = "disconnect", method = RequestMethod.GET)
//    public String deconnecter() {
//        session.invalidate();
//        return "index";
//    }
//
//    @RequestMapping(value = "client", method = RequestMethod.POST)
//    protected ModelAndView handleClients(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String sourcePage = request.getParameter("page");
//        ModelAndView mv;
//        if (sourcePage.equals("index")) {
//            Long id = Long.valueOf(request.getParameter("numero_compte"));
//            String password = request.getParameter("password");
//            String typeClient = request.getParameter("typeClient");
//            String result = "Erreur! Aucun client/conseiller avec ces identifiants!";
//            mv = new ModelAndView("erreur");
//
//            if (typeClient.equals("particulier") && loginService.partriculierExistGetName(id, password) != null) {
//                session = request.getSession();
//                mv = new ModelAndView("client");
//                ModelMap m = mv.getModelMap();
//                result = "Bonjour " + loginService.partriculierExistGetName(id, password);
//                List<CompteEntity> comptes;
//                comptes = loadInfoService.partriculierGetComptes(id);
//                session.setAttribute("idUser", id);
//                session.setAttribute("typeUser", "particulier");
//                session.setAttribute("comptes", comptes);
//
//            } else if (typeClient.equals("professionnel") && loginService.professionnelExistGetName(id, password) != null) {
//                mv = new ModelAndView("client");
//                ModelMap m = mv.getModelMap();
//                result = "Bonjour " + loginService.professionnelExistGetName(id, password);
//                List<CompteEntity> comptes;
//                comptes = loadInfoService.professionnelGetComptes(id);
//                session = request.getSession();
//                session.setAttribute("idUser", id);
//                session.setAttribute("typeUser", "professionnel");
//                session.setAttribute("comptes", comptes);
//
//            } else if (typeClient.equals("conseiller") && loginService.conseillerExistGetName(id, password) != null) {
//                mv = new ModelAndView("redirect:/conseillers.htm");
//                session = request.getSession();
//                session.setAttribute("idUser", id);
//                session.setAttribute("typeUser", "conseiller");
//                return mv;
//            }
//            mv.addObject("result", result);
//            return mv;
//        } else {
//            if (session != null && session.getAttribute("idUser") != null) {
//                mv = new ModelAndView("client");
//                ModelMap m = mv.getModelMap();
//                Long idCompteSender = Long.valueOf(request.getParameter("idCompteSender"));
//                Long idCompteReceiver = Long.valueOf(request.getParameter("idCompteReceiver"));
//                Double montant = Double.valueOf(request.getParameter("montant"));
//                Long idUser = (Long) (session.getAttribute("idUser"));
//                String typeUser = (String) session.getAttribute("typeUser");
//                String result;
//                if (virementService.CompteGetSolde(idCompteSender) < montant) {
//                    result = "Virement non réussi! le solde de votre compte est inférieur du montant à envoyer";
//                } else if (virementService.CompteGetPlafond(idCompteReceiver) != null && virementService.CompteGetSolde(idCompteReceiver) + montant > virementService.CompteGetPlafond(idCompteReceiver)) {
//                    result = "Virement non réussi! le solde de votre compte destination risque de dépasser le plafond";
//                } else if (virementService.CompteGetType(idCompteSender).equals("Livret") && virementService.CompteGetMinimum(idCompteSender) != null && virementService.CompteGetSolde(idCompteSender) - montant < virementService.CompteGetMinimum(idCompteSender)) {
//                    result = "Virement non réussi! le solde de votre compte source risque dépasser le minimum";
//                } else {
////                    virementService.insertTransactions(virementService.lastId(), idCompteSender, idCompteReceiver, montant);
////                    virementService.insertIntoComptes(idCompteSender, idCompteReceiver, montant);
//                    virementService.insertIntoComptesAndTransaction(idCompteSender, idCompteReceiver, montant, virementService.lastId());
//                    result = "Virement réussi";
//                    List<CompteEntity> comptes;
//                    if (typeUser.equals("particulier")) {
//                        comptes = loadInfoService.partriculierGetComptes(idUser);
//                        session.setAttribute("comptes", comptes);
//                    } else if (typeUser.equals("professionnel")) {
//                        comptes = loadInfoService.professionnelGetComptes(idUser);
//                        session.setAttribute("comptes", comptes);
//                    }
//                }
//                mv.addObject("result", result);
//                return mv;
//            } else {
//                mv = new ModelAndView("index");
//                return mv;
//            }
//        }
//    }
//
//    @RequestMapping(value = "transactions", method = RequestMethod.POST)
//    protected ModelAndView handleTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ModelAndView mv;
//        if (session != null && session.getAttribute("idUser") != null) {
//            Long idCompte = Long.valueOf(request.getParameter("idCompte"));
//            mv = new ModelAndView("transactions");
//
//            if ((loadInfoService.compteGetTransactions(idCompte).get(0)) != null) {
//                ModelMap m = mv.getModelMap();
//                List<TransactionEntity> transactions = loadInfoService.compteGetTransactions(idCompte);
//                m.addAttribute("transactions", transactions);
//                mv.addObject("idCompte", idCompte);
//                mv.addObject("message", "Oprérations réalisées sur le compte numéro:");
//                return mv;
//
//            } else {
//                mv.addObject("idCompte", idCompte);
//                mv.addObject("message", "Aucune transaction réalisée sur ce compte:");
//                return mv;
//
//            }
//        } else {
//            mv = new ModelAndView("index");
//            return mv;
//        }
//    }
//
//    @RequestMapping(value = "virement", params = {"idUser", "typeUser"}, method = RequestMethod.GET)
//    public String initVirement(@RequestParam("idUser") Long idUser, @RequestParam("typeUser") String typeUser, Model model) {
//        if (session != null && session.getAttribute("idUser") != null) {
//            List<CompteEntity> comptes;
//            if (typeUser.equals("particulier")) {
//                comptes = loadInfoService.partriculierGetComptes(idUser);
//                session.setAttribute("comptes", comptes);
//                session.setAttribute("idUser", idUser);
//                session.setAttribute("typeUser", typeUser);
//                System.out.println("id user avant y aller a virement : " + idUser);
//                return "virement";
//            } else if (typeUser.equals("professionnel")) {
//                comptes = loadInfoService.professionnelGetComptes(idUser);
//                session.setAttribute("comptes", comptes);
//                session.setAttribute("idUser", idUser);
//                session.setAttribute("typeUser", "professionnel");
//
//                return "virement";
//            } else {
//                return null;
//            }
//        } else {
//            return "index";
//        }
//    }
//
//    @RequestMapping(value = "conseillers", method = RequestMethod.GET)
//    public ModelAndView initConseillers() {
//        ModelAndView mv;
//        if (session != null && session.getAttribute("idUser") != null && session.getAttribute("typeUser").equals("conseiller")) {
//
//            mv = new ModelAndView("conseillers");
//            Long idConseiller = (Long) session.getAttribute("idUser");
//            System.out.println("id coneiller **********************************" + idConseiller);
//            List<ProfessionnelEntity> professionnels = loadInfoService.conseillerGetProfessionnels(idConseiller);
//            List<ParticulierEntity> particuliers = loadInfoService.conseillerGetParticuliers(idConseiller);
//            List<CompteEntity> c;
//            List<CompteEntity> comptes = new ArrayList<>();
//            for (int i = 0; i < professionnels.size(); i++) {
//                c = loadInfoService.professionnelGetComptes(professionnels.get(i).getId());
//                comptes.addAll(c);
//            }
//            for (int i = 0; i < particuliers.size(); i++) {
//                c = loadInfoService.partriculierGetComptes(particuliers.get(i).getId());
//                comptes.addAll(c);
//            }
//            session.setAttribute("professionnels", professionnels);
//            session.setAttribute("particuliers", particuliers);
//            session.setAttribute("comptes", comptes);
//
//        } else {
//            mv = new ModelAndView("erreur");
//            mv.addObject("result", "Page non accessible ou session fermée!");
//        }
//        return mv;
//    }
//
//    @RequestMapping(value = "client", method = RequestMethod.GET)
//    public String initClient() {
//        if (session != null && session.getAttribute("idUser") != null) {
//            return "client";
//        } else {
//            return "index";
//        }
//    }
//
//    @RequestMapping(value = "virement", method = RequestMethod.GET)
//    public String iniVirement() {
//        if (session != null && session.getAttribute("idUser") != null) {
//            return "virement";
//        } else {
//            return "index";
//        }
//    }
}
