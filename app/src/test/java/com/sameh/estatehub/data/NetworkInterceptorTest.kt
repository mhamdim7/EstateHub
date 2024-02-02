package com.sameh.estatehub.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException


class NetworkInterceptorTest {

    private lateinit var context: Context
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var chain: okhttp3.Interceptor.Chain
    private lateinit var request: okhttp3.Request
    private lateinit var response: okhttp3.Response

    private lateinit var networkInterceptor: NetworkInterceptor

    @Before
    fun setup() {
        context = mockk()
        connectivityManager = mockk()
        chain = mockk()
        request = mockk()
        response = mockk()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { connectivityManager.activeNetwork } returns null

        networkInterceptor = NetworkInterceptor(context)
        mockkStatic(Context::class)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test(expected = NetworkInterceptor.NoConnectivityException::class)
    fun testNoInternetConnectivity() {
        //given
        val network = mockNetwork(false)
        every { connectivityManager.activeNetwork } returns network
        //then
        networkInterceptor.intercept(chain)
    }

    @Test(expected = NetworkInterceptor.NetworkException::class)
    fun testNetworkException() {
        //given
        every { chain.request() } returns request
        every { chain.proceed(request) } throws IOException()
        val network = mockNetwork(true)
        every { connectivityManager.activeNetwork } returns network
        //then
        networkInterceptor.intercept(chain)
    }

    @Test(expected = NetworkInterceptor.ClientErrorException::class)
    fun testClientErrorException() {
        //given
        every { chain.request() } returns request
        every { chain.proceed(request) } returns response
        every { response.isSuccessful } returns false
        every { response.code } returns 400
        val network = mockNetwork(true)
        every { connectivityManager.activeNetwork } returns network
        //then
        networkInterceptor.intercept(chain)
    }

    @Test(expected = NetworkInterceptor.ServerErrorException::class)
    fun testServerErrorException() {
        //given
        every { chain.request() } returns request
        every { chain.proceed(request) } returns response
        every { response.isSuccessful } returns false
        every { response.code } returns 500
        val network = mockNetwork(true)
        every { connectivityManager.activeNetwork } returns network
        //then
        networkInterceptor.intercept(chain)
    }

    private fun mockNetwork(hasInternet: Boolean): Network {
        val network = mockk<Network>()
        val capabilities = mockk<NetworkCapabilities>()
        every { capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns hasInternet
        every { connectivityManager.getNetworkCapabilities(network) } returns capabilities
        return network
    }
}





