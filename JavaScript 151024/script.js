
const hour = prompt("Enter time (0 - 24):");

const time = Number(hour);

if (time >= 5 && time < 12) {
    document.getElementById("greeting").textContent = "Good Morning";
} else if (time >= 12 && time < 17) {
    document.getElementById("greeting").textContent = "Good Afternoon";
} else if (time >= 17 && time < 21) {
    document.getElementById("greeting").textContent = "Good Evening";
} else {
    document.getElementById("greeting").textContent = "Good Night";
}
