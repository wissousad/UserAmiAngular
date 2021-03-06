/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import dao.CompteEntity;
import dao.ConseillerEntity;
import dao.ParticulierDAO;
import dao.ParticulierEntity;
import dao.ProfessionnelEntity;
import dao.TransactionEntity;
import dao.ProfessionnelDAO;
import dao.ConseillerDAO;

import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
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
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.json.JSONObject;

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
    @Autowired
    private ProfessionnelDAO professionnelDAO;
    @Autowired
    private ConseillerDAO conseillerDAO;

    public void setParticulierDAO(ParticulierDAO particulierDAO) {
        this.particulierDAO = particulierDAO;
    }

    //session
    static HttpSession session;

    private JSONObject requestToJSONObj(HttpServletRequest request) throws JSONException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        try {
            String str ;
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
        JSONObject jObj = requestToJSONObj(request);
        System.out.println("***********************requete JSON************************");
        System.out.println(jObj);
        List<CompteEntity> comptes;
        String clientResponseStr;
        JsonConfig jsonConfig;
        net.sf.json.JSONObject clientResponseJObj;

        String typeClient = jObj.getString("typeClient");
        System.out.println("*************************typeClient = " + typeClient);
        Long numero_compte = Long.valueOf(jObj.getString("numero_compte"));
        switch (typeClient) {
            case "particulier": {
                ParticulierEntity client = particulierDAO.find(numero_compte);
                if (client != null) {
                    out.println("************particuluer***********");

                    comptes = loadInfoService.partriculierGetComptes(numero_compte);
                    clientResponseStr = client.loginPassToJSON();
                    clientResponseJObj = net.sf.json.JSONObject.fromObject(clientResponseStr);
                    jsonConfig = new JsonConfig();
                    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
                    clientResponseJObj.accumulate("comptes", comptes, jsonConfig);
                    out.println("***********jSONArray************" + clientResponseJObj);
                    return new ResponseEntity(clientResponseJObj + "", HttpStatus.OK);
                }
                break;
            }
            case "professionnel": {
                ProfessionnelEntity client = professionnelDAO.find(numero_compte);
                if (client != null) {
                    clientResponseStr = client.loginPassToJSON();
                    clientResponseJObj = net.sf.json.JSONObject.fromObject(clientResponseStr);
                    comptes = loadInfoService.professionnelGetComptes(numero_compte);
                    jsonConfig = new JsonConfig();
                    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
                    clientResponseJObj.accumulate("comptes", comptes, jsonConfig);
                    out.println("***********jSONArray************" + clientResponseJObj);
                    return new ResponseEntity(clientResponseJObj + "", HttpStatus.OK);
                }
                break;
            }
            case "conseiller": {
                // ici numero_compte c'est l'id du conseiller, une appelation seulement
                ConseillerEntity client = conseillerDAO.find(numero_compte);
                if (client != null) {
                    clientResponseStr = client.loginPassToJSON();
                    clientResponseJObj = net.sf.json.JSONObject.fromObject(clientResponseStr);
                    List<ProfessionnelEntity> professionnels = loadInfoService.conseillerGetProfessionnels(numero_compte);
                    List<ParticulierEntity> particuliers = loadInfoService.conseillerGetParticuliers(numero_compte);
                    List<CompteEntity> c;
                    comptes = new ArrayList<>();
                    for (int i = 0; i < professionnels.size(); i++) {
                        c = loadInfoService.professionnelGetComptes(professionnels.get(i).getId());
                        comptes.addAll(c);
                        System.out.println("i");
                    }
                    for (int i = 0; i < particuliers.size(); i++) {
                        c = loadInfoService.partriculierGetComptes(particuliers.get(i).getId());
                        comptes.addAll(c);
                        System.out.println("i");
                        System.out.println("++++++++++++++++++");
                    }
                    jsonConfig = new JsonConfig();
                    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
                    clientResponseJObj.accumulate("comptes", comptes, jsonConfig);
                    clientResponseJObj.accumulate("particuliers", particuliers, jsonConfig);
                    clientResponseJObj.accumulate("professionnels", professionnels, jsonConfig);

//                    String clientResponse = clientResponseJObj.toString();
//                    System.out.println("++++ comptes +++++");
//                    for (int i =0; i<comptes.size(); i++){
//                        System.out.println(comptes.get(i).toJSON()); 
//                    }
//                    if (!comptes.isEmpty()) {
//                        String cm = "";
//                        for (int i = 0; i < comptes.size(); i++) {
//                            cm = cm + comptes.get(i).toJSON() + " ,";
//                        }
//                        cm = cm.substring(0, cm.length() - 2);
//                        cm = ", \"comptes\" : [ " + cm + "] }";
//                        // enlever l'accolade de la fin
//                        clientResponse = clientResponse.substring(0, clientResponse.length() - 2);
//                        clientResponse += cm;
                    out.println("***********jSONArray************" + clientResponseJObj);
                    return new ResponseEntity(clientResponseJObj + "", HttpStatus.OK);
                }
                break;
            }

            default:
                break;
        }

        return new ResponseEntity(
                "[]", HttpStatus.OK);
    }

    @RequestMapping(value = "getTransactions", method = RequestMethod.POST)
    public ResponseEntity<?> getTransactions(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        JSONObject jObj = requestToJSONObj(request);
        System.out.println("***********************requete JSON**jobj**********************" + jObj);
        // Long idCompte = Long.valueOf(jObj.getString("idCompte"));
        Long idCompte = jObj.getLong("idCompte");
        if ((loadInfoService.compteGetTransactions(idCompte).get(0)) != null) {
            List<TransactionEntity> transactions = loadInfoService.compteGetTransactions(idCompte);
            String tr = "";
            for (int i = 0; i < transactions.size(); i++) {
                tr += transactions.get(i).toJSON() + " ,";
            }
            tr = tr.substring(0, tr.length() - 2);
            tr = "{ \"transactions\" : [ " + tr + "] }";

//            net.sf.json.JSONObject clientResponseJObj = new net.sf.json.JSONObject();
//            JsonConfig jsonConfig = new JsonConfig();
//            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//            clientResponseJObj.accumulate("transactions", transactions, jsonConfig);
//            out.println("***********jSONArray************" + clientResponseJObj);

            /*  net.sf.json.JSONArray clientResponseArr = new net.sf.json.JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            clientResponseArr.addAll( transactions, jsonConfig);
            String clientResponseJObj = "{ \"transactions\" : "+ clientResponseArr + "}";
            System.out.println(clientResponseJObj);*/
            System.out.println(tr);
            return new ResponseEntity(tr, HttpStatus.OK);

        } else {
            return new ResponseEntity("[]", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    public ResponseEntity<?> transfer(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        JSONObject jObj = requestToJSONObj(request);
        System.out.println("***********************requete JSON**jobj**********************" + jObj);
        Long idCompteSender = jObj.getLong("idCompteSender");
        Long idCompteReceiver = jObj.getLong("idCompteReceiver");
        int montant = jObj.getInt("montant");
        // String typeClient = jObj.getString("typeClient");
        String result;
        if (!virementService.receiverExists(idCompteReceiver)) {
            result = "Le compte destinataire n'existe pas!";

        } else if (virementService.CompteGetSolde(idCompteSender) < montant) {
            result = "Virement non reussi! le solde de votre compte est inferieur du montant a envoyer";
        } else if (virementService.CompteGetPlafond(idCompteReceiver) != null && virementService.CompteGetSolde(idCompteReceiver) + montant > virementService.CompteGetPlafond(idCompteReceiver)) {
            result = "Virement non reussi! le solde de votre compte destination risque de depasser le plafond";
        } else if (virementService.CompteGetType(idCompteSender).equals("Livret") && virementService.CompteGetMinimum(idCompteSender) != null && virementService.CompteGetSolde(idCompteSender) - montant < virementService.CompteGetMinimum(idCompteSender)) {
            result = "Virement non reussi! le solde de votre compte source risque dépasser le minimum";
        } else {
            virementService.insertTransactions(virementService.lastId(), idCompteSender, idCompteReceiver, montant);
            virementService.insertIntoComptes(idCompteSender, idCompteReceiver, montant);
            virementService.insertIntoComptesAndTransaction(idCompteSender, idCompteReceiver, montant, virementService.lastId());
            result = "Success";
        }
        result = result.toUpperCase();
        result = "{ \"message\" :  \"" + result + "\" }";
        System.out.println("value of reponse " + result);
        return new ResponseEntity(result, HttpStatus.OK);

    }

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
