let students = [];


function addStudent() {
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;
    const grade = document.getElementById('grade').value;
    
    const student = { name, age, grade };
    students.push(student);

    
    displaySuccessMessage();
    
    
    document.getElementById('student-form').reset();

    return false; 
}


function displaySuccessMessage() {
    const successMessageDiv = document.getElementById('success-message');
    successMessageDiv.innerHTML = `<h3 style="color: green;">Student Added Successfully</h3>`;
    
    setTimeout(() => {
        successMessageDiv.innerHTML = '';
    }, 3000);
}


function displayStudents() {
    const studentList = document.getElementById('student-list');
    studentList.innerHTML = '<h2>Student List</h2>';

    students.forEach((student, index) => {
        studentList.innerHTML += `<li>${student.name}, Age: ${student.age}, Grade: ${student.grade} <button onclick="removeStudent(${index})">Delete</button></li>`;
    });
}


function sortStudents() {
    students.sort((a, b) => b.age - a.age); 
    displayStudents(); 
}


function removeStudent(index) {
    const confirmation = confirm("Are you sure you want to delete this student?");
    if (confirmation) {
        students.splice(index, 1); 
        displayStudents(); 
    }
}
