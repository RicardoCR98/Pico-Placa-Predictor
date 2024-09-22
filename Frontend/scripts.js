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
            fetch('http://backend:8080/predict', { //Here change de url
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
                data.canDrive ? resultDiv.classList.add('text-success') : resultDiv.classList.add('text-danger')
                resultDiv.textContent = data.message;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

    });
});
