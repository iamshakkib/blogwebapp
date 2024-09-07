import React, { useEffect, useState } from 'react';

export const Aidacs: React.FC = () => {
    const [data, setData] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:9292/'); // Replace with your actual API endpoint
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const result = await response.text();
                setData(result);
            } catch (error: any) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []); // Empty dependency array ensures this runs only once after the initial render

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="bg-slate-200 flex-col">
            <h1>Aidacs</h1>
            <p>{data}</p>
        </div>
    );
};
