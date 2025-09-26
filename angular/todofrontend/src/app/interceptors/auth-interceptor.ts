import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  localStorage.setItem('token', 'admin-token-xyz');
  const token = localStorage.getItem('token') || '';

  if (req.url.startsWith('http://localhost:8090/api')) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req);
};
