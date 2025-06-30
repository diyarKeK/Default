const form = document.getElementById('register-form')
const messageBox = document.getElementById('form-message')

document.getElementById('google-signup').addEventListener('click', () => {
  showMessage('We are sorry. Signing up with Google is not available now', 'warning')
})

form.addEventListener('submit', async function (e) {
  e.preventDefault()

  const name = document.getElementById('name').value.trim()
  const email = document.getElementById('email').value.trim()
  const password = document.getElementById('password').value.trim()
  const bio = document.getElementById('bio').value.trim()
  const agreed = document.getElementById('terms').checked

  if (name.length > 32) 
    return showMessage('Name must be at most 32 characters.', 'error')
  if (email.length > 256) 
    return showMessage('Email must be at most 256 characters.', 'error')
  if (password.length > 256) 
    return showMessage('Password must be at most 256 characters.', 'error')
  if (bio.length > 1024) 
    return showMessage('Bio must be at most 1024 characters.', 'error')
  if (!agreed) 
    return showMessage('You must agree to the terms and conditions.', 'error')

  const data = {
    name,
    email,
    password,
    bio,
    roles: ["USER"]
  }

  try {
    const response = await fetch('/api/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
      credentials: 'include'
    })

    if (!response.ok) {
      const errorText = await response.text()
      showMessage(errorText || 'Registration failed.', 'error')
      return
    }
    
    showMessage('Registration successful!', 'success')
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
