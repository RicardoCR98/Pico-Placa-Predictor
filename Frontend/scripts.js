document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('predictForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault();  

        const dateInput = document.getElementById('datePicker').value;
        const timeInput = document.getElementById('timePicker').value;
        const plateNumber = document.getElementById('inputText').value;
        if (dateInput && timeInput) {
            const [year, month, day] = dateInput.split('-');
            const formattedDate = `${day}-${month}-${year}`;

            const requestData = {
                licensePlateNumber:plateNumber ,
                date:formattedDate,
                time: timeInput
            };
            fetch('/predict', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Prediction Result:', data);
                const resultDiv = document.getElementById('result');    
                const successClass = 'text-success';
                const dangerClass = 'text-danger';
                resultDiv.classList.remove(successClass, dangerClass);
                resultDiv.classList.add(data.canDrive ? successClass : dangerClass);
                resultDiv.textContent = data.message;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

    });
});
