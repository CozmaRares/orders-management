package com.raru.presentation;

import java.awt.Choice;
import java.awt.Component;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import com.raru.data.DBException;
import com.raru.logic.BillLogic;
import com.raru.logic.CategoryLogic;
import com.raru.logic.ClientLogic;
import com.raru.logic.OrderLogic;
import com.raru.logic.ProductLogic;
import com.raru.model.Bill;
import com.raru.model.Category;
import com.raru.model.Client;
import com.raru.model.Order;
import com.raru.model.Product;
import com.raru.presentation.views.actions.ActionSelectView;
import com.raru.presentation.views.actions.BillActionsView;
import com.raru.presentation.views.actions.CategoryActionsView;
import com.raru.presentation.views.actions.ClientActionsView;
import com.raru.presentation.views.actions.OrderActionsView;
import com.raru.presentation.views.actions.ProductActionsView;
import com.raru.presentation.views.data.AddProductView;
import com.raru.presentation.views.data.DataCreateView;
import com.raru.presentation.views.data.DataDeleteView;
import com.raru.presentation.views.data.DataReadView;
import com.raru.presentation.views.data.DataUpdateView;
import com.raru.presentation.views.data.SelectView;
import com.raru.presentation.views.utils.ErrorView;
import com.raru.presentation.views.utils.SuccessView;
import com.raru.utils.NumberField;

public class Controller {
    private ActionSelectView actionSelectView;

    public Controller() {
        setupActionSelectView();
    }

    private void setupActionSelectView() {
        actionSelectView = new ActionSelectView();

        actionSelectView.setBillButtonListener(event -> {
            setupBillActionsView();
        });

        actionSelectView.setCategoryButtonListener(event -> {
            setupCategoryActionsView();
        });

        actionSelectView.setClientButtonListener(event -> {
            setupClientActionsView();
        });

        actionSelectView.setOrderButtonListener(event -> {
            setupOrderActionsView();
        });

        actionSelectView.setProductButtonListener(event -> {
            setupProductActionsView();
        });
    }

    /**
     * Creates the BillActionsView and configures its button listeners.
     */
    private void setupBillActionsView() {
        var billActionsView = new BillActionsView();

        billActionsView.setReadButtonListener(event -> {
            try {
                new DataReadView(BillLogic.findMany(), Bill.class);
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });
    }

    private Map<String, JTextField> createCategoryFields() {
        return Map.of("name", new JTextField());
    }

    /**
     * Creates the CategoryActionsView and configures its button listeners.
     */
    private void setupCategoryActionsView() {
        var categoryActionsView = new CategoryActionsView();

        categoryActionsView.setCreateButtonListener(event -> {
            Map<String, JTextField> fields = createCategoryFields();
            var createView = new DataCreateView(fields);

            createView.setButtonListener(e -> {
                String name = fields.get("name").getText();

                try {
                    CategoryLogic.create(name);
                    new SuccessView();
                } catch (DBException ex) {
                    new ErrorView(ex.getMessage());
                }
            });
        });

        categoryActionsView.setReadButtonListener(event -> {
            try {
                new DataReadView(CategoryLogic.findMany(), Category.class);
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        categoryActionsView.setUpdateButtonListener(event -> {
            try {
                Map<String, Category> categories = CategoryLogic
                        .findMany()
                        .stream()
                        .map(c -> new AbstractMap.SimpleEntry<>(c.getId(), c))
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
                Map<String, JTextField> fields = createCategoryFields();
                Collection<String> ids = categories.keySet();

                var firstCategory = categories.entrySet().iterator().next().getValue();

                fields.get("name").setText(firstCategory.getName());

                var updateView = new DataUpdateView(fields, ids);

                updateView.setChoiceListener(item -> {
                    var category = categories.get(item.getItem());
                    fields.get("name").setText(category.getName());
                });

                updateView.setButtonListener(e -> {
                    var category = categories.get(updateView.getSelected());
                    String name = fields.get("name").getText();
                    category.setName(name);
                    new SuccessView();
                    try {
                        CategoryLogic.update(category);
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        categoryActionsView.setDeleteButtonListener(event -> {
            try {
                List<String> categoryList = CategoryLogic
                        .findMany()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());

                var deleteView = new DataDeleteView(categoryList);

                deleteView.setButtonListener(e -> {
                    try {
                        CategoryLogic.delete(deleteView.getSelected());
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        categoryActionsView.setAddProductButtonListener(event -> {
            try {
                List<Category> categories = CategoryLogic.findMany();
                List<Product> products = ProductLogic.findMany();

                var addProductView = new AddProductView(categories, products);

                addProductView.setButtonListener(e -> {
                    var categoryID = addProductView.getSelectedCategory();
                    var productID = addProductView.getSelectedProduct();

                    try {
                        CategoryLogic.addProduct(categoryID, productID);
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });
    }

    private Map<String, JTextField> createClientFields() {
        return Map.of(
                "name", new JTextField(),
                "email", new JTextField());
    }

    /**
     * Creates the ClientActionsView and configures its button listeners.
     */
    private void setupClientActionsView() {
        var clientActionsView = new ClientActionsView();

        clientActionsView.setCreateButtonListener(event -> {
            Map<String, JTextField> fields = createClientFields();
            var createView = new DataCreateView(fields);

            createView.setButtonListener(e -> {
                String name = fields.get("name").getText();
                String email = fields.get("email").getText();

                try {
                    ClientLogic.create(name, email);
                    new SuccessView();
                } catch (Exception ex) {
                    new ErrorView(ex.getMessage());
                }
            });
        });

        clientActionsView.setReadButtonListener(event -> {
            try {
                new DataReadView(ClientLogic.findMany(), Client.class);
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        clientActionsView.setUpdateButtonListener(event -> {
            try {
                Map<String, Client> clients = ClientLogic
                        .findMany()
                        .stream()
                        .map(c -> new AbstractMap.SimpleEntry<>(c.getId(), c))
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
                Map<String, JTextField> fields = createClientFields();
                Collection<String> ids = clients.keySet();

                var firstClient = clients.entrySet().iterator().next().getValue();

                fields.get("name").setText(firstClient.getName());
                fields.get("email").setText(firstClient.getEmail());

                var updateView = new DataUpdateView(fields, ids);

                updateView.setChoiceListener(item -> {
                    var client = clients.get(item.getItem());
                    fields.get("name").setText(client.getName());
                    fields.get("email").setText(client.getEmail());
                });

                updateView.setButtonListener(e -> {
                    var client = clients.get(updateView.getSelected());
                    String name = fields.get("name").getText();
                    String email = fields.get("email").getText();
                    client.setName(name);
                    client.setEmail(email);

                    try {
                        ClientLogic.update(client);
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        clientActionsView.setDeleteButtonListener(event -> {
            try {
                List<String> clientList = ClientLogic
                        .findMany()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());

                var deleteView = new DataDeleteView(clientList);

                deleteView.setButtonListener(e -> {
                    try {
                        ClientLogic.delete(deleteView.getSelected());
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        clientActionsView.setProductsButtonListener(event -> {
            try {
                List<String> ids = ClientLogic
                        .findMany()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());
                var selectView = new SelectView(ids);

                selectView.setButtonListener(e -> {
                    try {
                        new DataReadView(ClientLogic.getOrderedProducts(selectView.getSelected()),
                                Product.class);
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });

            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });
    }

    private Map<String, Component> createOrderFields() throws DBException {
        var clientChoice = new Choice();
        var productChoice = new Choice();

        ClientLogic.findMany().forEach(c -> clientChoice.add(c.getId()));
        ProductLogic.findMany().forEach(p -> productChoice.add(p.getId()));

        return Map.of(
                "clientID", clientChoice,
                "productID", productChoice,
                "dueUntilDuration", new JTextField());
    }

    /**
     * Creates the OrderActionsView and configures its button listeners.
     */
    private void setupOrderActionsView() {
        var orderActionsView = new OrderActionsView();

        orderActionsView.setCreateButtonListener(event -> {
            try {
                Map<String, Component> fields = createOrderFields();
                var createView = new DataCreateView(fields);

                createView.setButtonListener(e -> {
                    String clientID = ((Choice) fields.get("clientID")).getSelectedItem();
                    String productID = ((Choice) fields.get("productID")).getSelectedItem();
                    String dueUntilDuration = ((JTextField) fields.get("dueUntilDuration")).getText();

                    try {
                        OrderLogic.create(clientID, productID, dueUntilDuration);
                        new SuccessView();
                    } catch (Exception ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        orderActionsView.setReadButtonListener(event -> {
            try {
                new DataReadView(OrderLogic.findMany(), Order.class);
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });
    }

    private Map<String, JTextField> createProductFields() {
        return Map.of(
                "name", new JTextField(),
                "brand", new JTextField(),
                "price", new NumberField(0),
                "quantity", new NumberField(0));
    }

    /**
     * Creates the ProductActionsView and configures its button listeners.
     */
    private void setupProductActionsView() {
        var productActionsView = new ProductActionsView();

        productActionsView.setCreateButtonListener(event -> {
            Map<String, JTextField> fields = createProductFields();
            var createView = new DataCreateView(fields);

            createView.setButtonListener(e -> {
                String name = fields.get("name").getText();
                String brand = fields.get("brand").getText();
                double price = Double.parseDouble(fields.get("price").getText());
                int quantity = Integer.parseInt(fields.get("quantity").getText());

                try {
                    ProductLogic.create(name, brand, price, quantity);
                    new SuccessView();
                } catch (Exception ex) {
                    new ErrorView(ex.getMessage());
                }
            });
        });

        productActionsView.setReadButtonListener(event -> {
            try {
                new DataReadView(ProductLogic.findMany(), Product.class);
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        productActionsView.setUpdateButtonListener(event -> {
            try {
                Map<String, Product> products = ProductLogic
                        .findMany()
                        .stream()
                        .map(c -> new AbstractMap.SimpleEntry<>(c.getId(), c))
                        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
                Map<String, JTextField> fields = createProductFields();
                Collection<String> ids = products.keySet();

                var firsProduct = products.entrySet().iterator().next().getValue();

                fields.get("name").setText(firsProduct.getName());
                fields.get("brand").setText(firsProduct.getBrand());
                fields.get("price").setText("" + firsProduct.getPrice());

                var updateView = new DataUpdateView(fields, ids);

                updateView.setChoiceListener(item -> {
                    var product = products.get(item.getItem());
                    fields.get("name").setText(product.getName());
                    fields.get("brand").setText(product.getBrand());
                    fields.get("price").setText("" + product.getPrice());
                });

                updateView.setButtonListener(e -> {
                    var product = products.get(updateView.getSelected());
                    String name = fields.get("name").getText();
                    String brand = fields.get("brand").getText();
                    double price = Double.parseDouble(fields.get("price").getText());
                    product.setName(name);
                    product.setBrand(brand);
                    product.setPrice(price);

                    try {
                        ProductLogic.update(product);
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        productActionsView.setDeleteButtonListener(event -> {
            try {
                List<String> productList = ProductLogic
                        .findMany()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());

                var deleteView = new DataDeleteView(productList);

                deleteView.setButtonListener(e -> {
                    try {
                        ProductLogic.delete(deleteView.getSelected());
                        new SuccessView();
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });
            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });

        productActionsView.setClientsButtonListener(event -> {
            try {
                List<String> ids = ProductLogic
                        .findMany()
                        .stream()
                        .map(c -> c.getId())
                        .collect(Collectors.toList());
                var selectView = new SelectView(ids);

                selectView.setButtonListener(e -> {
                    try {
                        new DataReadView(ProductLogic.getOrderedBy(selectView.getSelected()),
                                Client.class);
                    } catch (DBException ex) {
                        new ErrorView(ex.getMessage());
                    }
                });

            } catch (DBException ex) {
                new ErrorView(ex.getMessage());
            }
        });
    }
}
