var OrderForm = function() {
    var self = {};

    self.calculate_total = function() {
        var total = parseFloat(self.get_price()) + parseFloat(self.get_total_tax());
        self.total.text(total);
        self.hidden_total.val(total);
    };

    self.get_price = function() {
        return self.get_formatted_field(self.price);
    };

    self.get_total_tax = function() {
        return parseFloat(self.get_formatted_field(self.price) * self.get_formatted_field(self.tax)).toFixed(2);
    };

    self.get_formatted_field = function(field) {
        return parseFloat(field.text()).toFixed(2);
    };

    self.get_action = function() {
        return "/twuFunctionalTesting/order/create?itemId=" + self.item_id;
    };

    self.update = function(new_price, new_tax, new_item_id) {
        self.price.text(new_price);
        self.tax.text(new_tax);
        self.item_id = new_item_id;
    };

    init = function(price_field, tax_field, total_field, item_selected) {
        self.price = price_field;
        self.tax = tax_field;
        self.total = total_field;
        self.item_id = item_selected;
        self.hidden_total = $("#hidden_current_total");

        return self;
    };

    return { init:init };
}();

$(function(){
    var selected_item_index = $("#items option:selected").val();
    var order_form = OrderForm.init($("#current_price"), $("#current_tax"), $("#current_total"), selected_item_index);
    order_form.calculate_total();

    $("#submitButton").click(function(event) {
        validator = OrderFormValidator.init($("#name_field"), $("#email_field"));
        if(validator.validate()) {
            var new_action = order_form.get_action();
            $("#newOrderForm").attr("action", new_action);
        } else {
            event.preventDefault();
        }
    });

    $("#items").change(function() {
        var id = $(this).val();
        var data_as_json;

        $.ajax({
            url: "/twuFunctionalTesting/item",
            data: {item_id : id},
            success: function(data) {
                data_as_json = JSON.parse(data);
                order_form.update(data_as_json["price"], data_as_json["tax"], id);
                order_form.calculate_total();
            },
            error: function() {
                alert('An error occurred, please try selecting another item.');
            }
        });
    });
});