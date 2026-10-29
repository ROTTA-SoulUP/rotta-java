package br.com.fiap.rottaapi;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class CorsFilter extends OncePerRequestFilter {

    private static final Set<String> ALLOWED_ORIGINS = Set.of(
            "http://localhost:5173",
            "https://rotta-frontend.vercel.app"
    );

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String origin = request.getHeader("Origin");

        if (origin != null && ALLOWED_ORIGINS.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Vary", "Origin");
            response.setHeader(
                    "Access-Control-Allow-Methods",
                    "GET, POST, PUT, PATCH, DELETE, OPTIONS"
            );
            response.setHeader(
                    "Access-Control-Allow-Headers",
                    "Content-Type, Authorization"
            );
            response.setHeader("Access-Control-Max-Age", "3600");
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
