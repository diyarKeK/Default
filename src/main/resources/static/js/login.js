const form = document.getElementById('login-form')
const messageBox = document.getElementById('form-message')

document.getElementById('google-signin').addEventListener('click', () => {
    showMessage('We are sorry. Signing in with Google is not available now', 'warning')
})

form.addEventListener('submit', async function (e) {
    e.preventDefault()

    const email = document.getElementById('email').value.trim()
    const password = document.getElementById('password').value.trim()
    const agreed = document.getElementById('terms').checked

    if (email.length > 256) 
        return showMessage('Email must be at most 256 characters.', 'error')
    if (password.length > 256)
        return showMessage('Password must be at most 256 characters.', 'error')
    if (!agreed)
        return showMessage('You must agree to the terms and conditions.', 'error')

    const data = {
        email, password
    }

    try {
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
            credentials: 'include'
        })

        if (!response.ok) {
            const errorText = await response.text()
            showMessage(errorText || 'Login failed', 'error')
            return
        }

        showMessage('Login successful!', 'success')
        setTimeout(() => window.location.href = '/', 2000)
    } catch (err) {
        showMessage('Error: ' + err.message, 'error')
    }
})

function showMessage(text, type) {
    messageBox.textContent = text
    messageBox.className = 'message ' + type
    messageBox.style.display = 'block'
}