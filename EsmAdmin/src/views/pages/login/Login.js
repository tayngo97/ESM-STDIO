import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
  CButton,
  CCard,
  CCardBody,
  CCardGroup,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
  CImage,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'
import axios from 'axios'

const Login = () => {
  const [userName, setUserName] = useState('')
  const [passWord, setPassWord] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleUserNameOnChange = (event) => {
    setUserName({
      userName: event.target.value,
    })
  }

  const handlePassWorkOnChange = (event) => {
    setPassWord({
      passWord: event.target.value,
    })
  }

  const handleLogin = (event) => {
    event.preventDefault()
    const data = {
      username: userName.userName,
      password: passWord.passWord,
    }
    axios
      .post('public/login', data)
      .then((res) => {
        localStorage.setItem('token', res.data.data.accessToken)
        localStorage.setItem('refreshToken', res.data.data.refreshToken)
        localStorage.setItem('userId', res.data.data.id)
        navigate('/dashboard', { state: { user: userName } })
      })
      .catch((err) => {
        setError('Incorrect account or password !')
      })
  }

  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={8}>
            <CCardGroup>
              <CCard className="p-4">
                <CCardBody>
                  <CForm>
                    <h1>Login</h1>
                    <p className="text-medium-emphasis">Sign In to your account</p>
                    <CInputGroup className="mb-3">
                      <CInputGroupText>
                        <CIcon icon={cilUser} />
                      </CInputGroupText>
                      <CFormInput
                        onChange={handleUserNameOnChange}
                        placeholder="Username"
                        autoComplete="username"
                      />
                    </CInputGroup>
                    <CInputGroup className="mb-4">
                      <CInputGroupText>
                        <CIcon icon={cilLockLocked} />
                      </CInputGroupText>
                      <CFormInput
                        onChange={handlePassWorkOnChange}
                        type="password"
                        placeholder="Password"
                        autoComplete="current-password"
                      />
                    </CInputGroup>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <CRow>
                      <CCol xs={6}>
                        <CButton onClick={handleLogin} color="dark" className="px-4">
                          Login
                        </CButton>
                      </CCol>
                      <CCol xs={6} className="text-right">
                        <CButton color="link" className="px-0">
                          Forgot password?
                        </CButton>
                      </CCol>
                    </CRow>
                  </CForm>
                </CCardBody>
              </CCard>
              <CCard className="text-white bg-dark py-5" style={{ width: '44%' }}>
                <CCardBody className="text-center">
                  <CImage
                    rounded
                    thumbnail
                    src="https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.6435-9/51753212_1009411635915332_1047119022539145216_n.png?_nc_cat=103&ccb=1-6&_nc_sid=09cbfe&_nc_ohc=8aZH6ys3tZoAX-rRrng&_nc_ht=scontent.fsgn2-2.fna&oh=00_AT-LiGTOntP9JyJz2mRITYyIWwR0d1XiarSj1OWFIySpOw&oe=62AACD95"
                    width={200}
                    height={200}
                  />
                  {/* <Link to="/register">
                    <CButton color="dark" className="mt-3" active tabIndex={-1}>
                      Register Now!
                    </CButton>
                  </Link> */}
                </CCardBody>
              </CCard>
            </CCardGroup>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Login
