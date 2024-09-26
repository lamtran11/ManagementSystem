// Function to populate departments in the dropdown
function loadDepartments() {
    fetch("http://localhost:8080/api/departments")
        .then(response => response.json())
        .then(departments => {
            const departmentSelect = document.getElementById("department");

            // Populate dropdown with departments
            departments.forEach(department => {
                const option = document.createElement("option");
                option.value = department.departmentName;
                option.textContent = department.departmentName;
                departmentSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error fetching departments:", error);
        });
}

// Function to fetch and populate courses based on the selected department
function loadCoursesByDepartment(departmentName) {
    fetch("http://localhost:8080/api/courses")
        .then(response => response.json())
        .then(courses => {
            const courseContainer = document.getElementById("course-container");
            courseContainer.innerHTML = "";  // Clear previous courses

            // Filter courses by selected department
            const filteredCourses = courses.filter(course => course.departmentName === departmentName);

            // Populate courses in the container
            filteredCourses.forEach(course => {
                const checkBox = document.createElement("input");
                checkBox.type = "checkbox";
                checkBox.name = "course";
                checkBox.value = course.courseName;

                const label = document.createElement("label");
                label.htmlFor = `course-${course.courseName}`;
                label.textContent = `${course.courseName}`;

                courseContainer.appendChild(checkBox);
                courseContainer.appendChild(label);
                courseContainer.appendChild(document.createElement("br"));
            });
        })
        .catch(error => {
            console.error("Error fetching courses:", error);
        });
}

// Event listener to load courses when a department is selected
// document.getElementById("department").addEventListener("change", function() {
//     const selectedDepartment = this.value;
//     if (selectedDepartment) {
//         loadCoursesByDepartment(selectedDepartment);
//     } else {
//         document.getElementById("course-container").innerHTML = "";  // Clear courses if no department selected
//     }
// });

// // Initial load of departments
// loadDepartments();


document.getElementById('student-register').addEventListener("submit" , function(event) {

    event.preventDefault(); //prevent default submit event

    const studentData = [{
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        email: document.getElementById('email').value,
        birthDate: document.getElementById('birthdate').value,
        address: document.getElementById('address').value,
        departmentName: document.getElementById('department').value,
        courseName: Array.from(document.querySelectorAll('input[name="course"]:checked'))
                        .map(course => course.value)
    }];

    console.log(studentData);

    fetch("http://localhost:8080/api/student/saveStudentFormData", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(studentData)  // Convert the data into JSON format
    })
    .then(response => response.json())
    .then(data => {
        console.log("Data submitted successfully:", data);
        // You can add further actions here, such as showing a success message
    })
    .catch(error => {
        console.error("Error submitting student data:", error);
        // You can add error handling logic here
    });
});



