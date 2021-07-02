package com.auth.server.services.positions.impl;

import com.auth.server.entity.position.Positions;
import com.auth.server.entity.positionscategory.PositionsCategories;
import com.auth.server.repository.PositionCategoryRepository;
import com.auth.server.repository.PositionsRepository;
import com.auth.server.services.positions.PositionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionsServiceImpl implements PositionsService {
    private final PositionCategoryRepository positionCategoryRepository;
    private final PositionsRepository positionsRepository;

    //administration full pack
    private final String[] positionsAdministration = {"Assistant Admin"};
    private final String[] positionsAdministrationDetails = {"As the job position suggests, assistant managers are responsible for helping the general admin with the execution of his tasks."};
    private final String[] getPositionsAdministrationImages = {"manager.png"};

    //Management full pack
    private final String[] positionsManagement = {"General Manager",
            "Assistant Manager",
            "Kitchen Manager",
            "Food & Beverage Manager",
            "Garden Manager",
            "Hall Manager",
            "Call Operator",
            "Delivery Manager"};
    private final String[] positionsManagementDetails = {
            "This is the most important position within your business when it comes to the operational part. General managers are those who focus on hiring/firing employees, training programs, PR and marketing, process optimization, etc. Consider them as the head of your restaurant",
            "As the job position suggests, assistant managers are responsible for helping the general manager with the execution of his tasks. They are often responsible for dealing with the paperwork, handling the training programs, taking part in brainstorming activities, helping with the decision-making processes, etc. When the general manager takes a day off, it is the Assistant who fills his position.",
            "This is basically the general manager of the kitchen. The duties, related to this position are focused on hiring and firing personnel, process management and optimization, inventory management, etc. The kitchen manager should be able to form a cohesive unit that works as a team and has one main goal – to achieve high customer satisfaction.",
            "This position is not so popular among the most restaurants as it is typical for the biggest ones. Food and beverage managers are responsible for inventory management (both for bar and kitchen products and supplies), monitoring whether the kitchen and the bar are in compliance with local health requirements and codes, as well as defining the menu items and the overall working processes within the restaurant (creating schedules for example).",
            "This position basically the general manager of the Restaurant Garden.",
            "This position basically the general manager of the Restaurant Garden. like gatherings, Wedding, ceremonies etc...",
            "This position basically the general manager of the accepting deliveries and orders.",
            "This position basically the general manager of the Delivering online or offline oerders to the customers outside the Restaurant."};

    private final String[] getPositionsManagementImages = {
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png"};

    //Financial full pack
    private final String[] positionsFinancials = {
            "Cashier",
            "Assistant Cashier"};
    private final String[] positionsFinancialsDetails = {
            "The cashier position is similar to the drive-thru operator as it is also responsible for handling cash operations and taking orders. The only difference between them is the fact that the drive-thru operator is located in (surprisingly)… a drive-thru restaurant.",
            "The cashier position is similar to the drive-thru operator as it is also responsible for handling cash operations and taking orders. The only difference between them is the fact that the drive-thru operator is located in (surprisingly)… a drive-thru restaurant as assistance."};
    private final String[] getPositionsFinancialsImages = {
            "manager.png",
            "manager.png"};

    //Stuff full pack
    private final String[] positionsStuff = {
            "Executive Chef",
            "Sous Chef",
            "Pastry Chef",
            "Line Cook",
            "Fast Food Cook",
            "Short Order Cook",
            "Prep cook",
            "Sommelier",
            "Server",
            "Runner",
            "Busser/Bus person",
            "Host/Hostess",
            "Bartender",
            "Barback",
            "Barista",
            "Dishwasher"};

    private final String[] positionsStuffDetails = {
            "If you aim at providing the best cuisine around town, then focus on finding the best executive chef out there. A good executive chef comes up with the meals on your menu. The great one helps you improve your overall service and tailor the food concept according to your restaurant’s needs. He also takes care of all cooking processes – from the preparation to the way it is served.",
            "This is basically the second most important position in the kitchen, after the executive chef. Consider sous chefs as assistants to the main man. If the executive chef takes a day off, it is the sous chef that takes charge of the kitchen. Sous chefs must be experienced and with similar skill sets as the executive chefs.",
            "Pastry chefs are responsible for the sweet treats in your menu. They develop recipes and prepares desserts such as cookies, cakes, souffles, crepes, mousses, etc. Consider hiring such if you have a big, casual restaurant. Otherwise, you may leave these duties to your other chefs, if they are experienced in pastry.",
            "This position is very important if you are running a bigger restaurant. The line cook handles various tasks that are intended to streamline the work of other cooks and the chefs. A line cook’s duties are related to taking care of one or multiple areas of the kitchen, thus ensuring the seamless and efficient work organization.",
            "If you are running a quick-service restaurant, this position is one of the most important ones for the overall success of your business. Fast food cooks are required to work under pressure and be able to prepare orders as quickly as possible. They usually work with kitchen equipment such as grills, deep fryers, sandwich makers, ovens, etc.",
            "As the name suggests, the people on this position are responsible for handling small orders for breakfasts or brunches. Short order cooks usually prepare salads, sandwiches, burgers or other types of light food that does not require significant time to prepare.   ",
            "The prep cook is the foundation of a successfully-run restaurant kitchen. Although employees on this position are not directly involved in the cooking processes, they are one of the main reasons why your dish is served so quickly. Usually, the prep cook position is typical for fine dining restaurants and its duties are related to the initial preparation of the ingredients needed for the menu items. Prep cooks are important when there is a high inflow of orders and chefs need to deal with them as quickly as possible. By having each ingredient initially prepared, meals are being cooked easily and in a timely manner.",
            "If you are running a fine dining or a restaurant, for which wine is a focal point of the whole experience, hiring a sommelier is a necessity. His duties are related to purchasing wine, creating a fine wine list, consulting customers or servers about the different types of wine and suggesting suitable combinations.",
            "If the host/hostess is the face of your restaurant, then the server is the heart. Good servers are usually those which can turn a not so pleasant customer experience into a great one and vice versa. Servers are responsible not only for taking orders and sending them to the kitchen and bar staff but to take personal care of each and every customer. This position requires a specific set of skills. A good server knows when he is needed and does not bother customers each and every 2 minutes to check whether everything is fine. On the other hand – the worse thing a customer can experience is to wait too long for the bill or to order. So make sure that your servers know the balance between both and can take care of your customers in a respective manner.",
            "Some restaurants prefer to hire runners in order to make the server’s work more efficient and easier. The main responsibility of the runner is to get the meal from the kitchen, once it is ready, and to serve it to the customer as soon as possible. That way, the food arrives on time and at the appropriate temperature.",
            "This position is related to table cleaning and preparation. Once a client leaves and a table becomes free, it’s the busser’s responsibility to clean it and prepare it for the next customers. Servers usually delegate less complex and more time-consuming tasks to bussers, such as providing additional utensils, serving butter, bread or water, filling empty cups, etc.",
            "The host/hostess is responsible for the customer’s first impression of your restaurant and service. Thus, hiring a cheerful person is essential, if you want to positively predispose your clients for their upcoming experience. The host/hostess usually greets your customers and takes them to their respective table. They also provide menus and assist with any initial information or questions that the clients may have. The people hired for this position are also responsible for answering the phone and making reservations.",
            "A great meal is nothing without a great drink, right? The bartender position is very important for the image of your restaurant. Make sure to hire an experienced bartender as this can define the overall customer satisfaction. Remember that the hired person for the position will not only be sitting behind the bar, isolated from the crowd. They will usually be taking orders and talking to your customers. That is why having a good bartender is a very important ingredient in the recipe for the success of your restaurant.",
            "Barbacks are baristas’ assistants. They are responsible for numerous tasks, but in general, they make sure that the bartender has everything needed to handle his job appropriately. They are responsible for maintaining the needed quantity of bottles, tracking inventory (coffee, sugar, fruits, etc.), restocking the bar with ice, changing kegs, etc.",
            "If you are running a small bakery or a fast-food restaurant, you will need to hire a barista. Apart from the dishes, people will be willing to order coffee, tea, smoothies, etc. The one you hire should be able to listen to customers carefully and process their orders in a timely manner.",
            "The cashier position is similar to the drive-thru operator as it is also responsible for handling cash operations and taking orders. The only difference between them is the fact that the drive-thru operator is located in (surprisingly)… a drive-thru restaurant."
    };

    private final String[] getPositionsStuffImages = {
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png",
            "manager.png"};

    @Override
    public void fillAdministrationData() {
        List<PositionsCategories> allCategories = positionCategoryRepository.findAll();
        for (PositionsCategories allCategory : allCategories) {
            if (allCategory.getPositionCategoryName().equalsIgnoreCase("Administration")) {
                for (int i = 0; i < positionsAdministration.length; i++) {
                    Positions positions = new Positions();
                    positions.setPositionsCategories(allCategory);
                    positions.setPositionName(positionsAdministration[i]);
                    positions.setPositionDetails(positionsAdministrationDetails[i]);
                    positions.setPositionIcon(getPositionsAdministrationImages[i]);
                    positionsRepository.save(positions);
                }
            }

        }
    }

    public void fillManagementData() {

        List<PositionsCategories> allCategories = positionCategoryRepository.findAll();
        for (PositionsCategories allCategory : allCategories) {
            if (allCategory.getPositionCategoryName().equalsIgnoreCase("Management")) {
                for (int i = 0; i < positionsManagement.length; i++) {
                    Positions positions = new Positions();
                    positions.setPositionsCategories(allCategory);
                    positions.setPositionName(positionsManagement[i]);
                    positions.setPositionDetails(positionsManagementDetails[i]);
                    positions.setPositionIcon(getPositionsManagementImages[i]);
                    positionsRepository.save(positions);
                }
            }
        }
    }

    public void fillFinancialData() {

        List<PositionsCategories> allCategories = positionCategoryRepository.findAll();
        for (PositionsCategories allCategory : allCategories) {
            if (allCategory.getPositionCategoryName().equalsIgnoreCase("Financials")) {
                for (int i = 0; i < positionsFinancials.length; i++) {
                    Positions positions = new Positions();
                    positions.setPositionsCategories(allCategory);
                    positions.setPositionName(positionsFinancials[i]);
                    positions.setPositionDetails(positionsFinancialsDetails[i]);
                    positions.setPositionIcon(getPositionsFinancialsImages[i]);
                    positionsRepository.save(positions);
                }
            }
        }
    }

    public void fillStuffData() {

        List<PositionsCategories> allCategories = positionCategoryRepository.findAll();
        for (PositionsCategories allCategory : allCategories) {
            if (allCategory.getPositionCategoryName().equalsIgnoreCase("Staff")) {
                for (int i = 0; i < positionsStuff.length; i++) {
                    Positions positions = new Positions();
                    positions.setPositionsCategories(allCategory);
                    positions.setPositionName(positionsStuff[i]);
                    positions.setPositionDetails(positionsStuffDetails[i]);
                    positions.setPositionIcon(getPositionsStuffImages[i]);
                    positionsRepository.save(positions);
                }
            }
        }
    }
}
