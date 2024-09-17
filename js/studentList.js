fetch('http://localhost:8080/student/findAll', {
  credentials: 'include' // Include credentials for session-based authentication
})
.then(response => {
  if (response.status === 401) { // Unauthorized
      window.location.href = '/login'; // Redirect to login if not authenticated
      return;
  }
  if (!response.ok) {
      throw new Error('Network response was not ok');
  }
  return response.json();
})
  .then((data) => {
    const studentTable = document.getElementById('studentTableBody');

    studentTable.innerHTML = ''; // Clear the table before appending new data

    data.forEach((student) => {
      const row = document.createElement('tr');

      const idCell = document.createElement('td');
      idCell.textContent = student.studentId || '未定義'; // '未定義' = 'undefined'
      row.appendChild(idCell);

      const firstNameCell = document.createElement('td');
      firstNameCell.textContent = student.firstName || '未定義';
      row.appendChild(firstNameCell);

      const lastNameCell = document.createElement('td');
      lastNameCell.textContent = student.lastName || '未定義';
      row.appendChild(lastNameCell);

      const emailCell = document.createElement('td');
      emailCell.textContent = student.email || '未定義';
      row.appendChild(emailCell);

      // Append the row to the student table body
      studentTable.appendChild(row);
    });
  })
  .catch((error) => {
    console.error('There has been a problem with your fetch operation:', error);
  });
