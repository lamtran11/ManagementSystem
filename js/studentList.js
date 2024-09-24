//Fetch data from dbs to table

fetch('http://localhost:8080/api/student/findAll')
    .then((response) => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then((data) => {
        const studentTable = document.getElementById('studentTableBody');
        studentTable.innerHTML = ''; // Clear the table before appending new data

        data.forEach((entry) => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = entry.studentId || '...'; // '...' = 'undefined'
            row.appendChild(idCell);

            const firstNameCell = document.createElement('td');
            firstNameCell.textContent = entry.firstName || '...';
            row.appendChild(firstNameCell);

            const lastNameCell = document.createElement('td');
            lastNameCell.textContent = entry.lastName || '...';
            row.appendChild(lastNameCell);

            const emailCell = document.createElement('td');
            emailCell.textContent = entry.email || '...';
            row.appendChild(emailCell);

            const addressCell = document.createElement('td');
            addressCell.textContent = entry.address || '...';
            row.appendChild(addressCell);

            const departmentCell = document.createElement('td');
            departmentCell.textContent = entry.departmentName || '...';
            row.appendChild(departmentCell);

            const courseCell = document.createElement('td');
            courseCell.textContent = entry.courseNames.join(', ') || '...';
            row.appendChild(courseCell);

            studentTable.appendChild(row);
        });
    })
    .catch((error) => {
        console.error('There has been a problem with your fetch operation:', error);
    });


//Add new student to dbs
