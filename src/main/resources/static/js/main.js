// main.js - JavaScript functions for E-commerce Store

document.addEventListener('DOMContentLoaded', function() {
    // Auto-dismiss alerts after 5 seconds
    setTimeout(function() {
        let alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            // Create Bootstrap alert instance and hide it
            let bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        });
    }, 5000);

    // Quantity input validation in product details
    const quantityInput = document.getElementById('quantity');
    if (quantityInput) {
        quantityInput.addEventListener('change', function() {
            const min = parseInt(this.getAttribute('min'));
            const max = parseInt(this.getAttribute('max'));
            const value = parseInt(this.value);

            if (value < min) {
                this.value = min;
            } else if (value > max) {
                this.value = max;
                alert(`Sorry, only ${max} items available in stock.`);
            }
        });
    }

    // Tooltips initialization
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
});